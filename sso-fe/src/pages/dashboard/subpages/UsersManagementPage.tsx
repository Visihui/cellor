import React,{ FC, useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import PageLayout from "../../../layouts/PageLayout";
import { Empty, Table, ConfigProvider, Avatar, Tooltip, Row, Button, Space, Popconfirm,Modal } from "antd";
import { ColumnsType } from "antd/lib/table";
import PubSub from 'pubsub-js' ;
import { fetchUsers } from "../../../api/UserApi";
import { UserItemDataType,UserInfoType } from "../../../api/UserApi";
import { PlusOutlined, FormOutlined, DeleteOutlined } from "@ant-design/icons";
import AddUser from "./AddUser";
import DrawCanvas from "./Canvas";
import { dropUser, searchUser } from '../../../services/UserService';

const UsersManagementPage: FC = () => {
    const initPageParams = {
        total: 1,
        pageSize: 10,        
        current: 1
    }

    const [ data, setData ] = useState<UserItemDataType[]>([]);
    const [ pageParams, setPageParams ] = useState(initPageParams);
    const [ isLoading, setIsLoading ] = useState<boolean>(false);
    const [ isEmpty, setIsEmpty ] = useState(true);
    const { t } = useTranslation();    
    const [ userData, setUserData ] = useState<UserInfoType|undefined>();   
   
    const columns: () => ColumnsType<UserItemDataType> = () => [
        Table.EXPAND_COLUMN,
        {
            title: t('users.columns.avatar'),
            dataIndex: "avatar",
            key: "avatar",
            align: "center",
            render: (avatar: string|null) => {
                if(avatar){
                    return <Avatar src={avatar} />
                }
            }
        },
        {
            title: t('users.columns.name'),
            dataIndex: "loginName",
            key: "loginName",
            align: "center",
        },
        {
            title: t('users.columns.chineseName'),
            dataIndex: "chineseName",
            key: "chineseName",
            align: "center",
        },
        {
            title: t('users.columns.englishName'),
            dataIndex: "englishName",
            key: "englishName",
            align: "center",
        },
        // {
        //     title: t('common.columns.createBy'),
        //     dataIndex: "createBy",
        //     key: "createBy",
        //     align: "center",
        //     render: (createBy: UserItemDataType|null) => {
        //         if(createBy){
        //             return(
        //                 <Tooltip title={`${createBy.name} ( ${createBy.chineseName} | ${createBy.englishName} )`}>
        //                     <Avatar src={createBy.avatar} />
        //                 </Tooltip>
        //             );
        //         }
        //     }
        // },
        // {
        //     title: t('common.columns.updateBy'),
        //     dataIndex: "updateBy",
        //     key: "updateBy",
        //     align: "center",
        //     render: (updateBy: UserItemDataType|null) => {
        //         if(updateBy){
        //             return(
        //                 <Tooltip title={`${updateBy.name} ( ${updateBy.chineseName} | ${updateBy.englishName} )`}>
        //                     <Avatar src={updateBy.avatar} />
        //                 </Tooltip>
        //             );
        //         }
        //     }
        // },
        {
            title: t('common.columns.createdAt'),
            dataIndex: "createTime",
            key: "createTime",
            align: "center"
        },
        {
            title: t('common.columns.updatedAt'),
            dataIndex: "updateTime",
            key: "updateTime",
            align: "center"
        },
        {
            title: t('common.columns.actions'),
            key: "actions",
            align: "center",
            render: (_, record: UserItemDataType) => (
                <Space size="middle">
                    <Tooltip title={t('common.updateAction')}>
                        <Button type="text" icon={<FormOutlined />} onClick={async () => {                       
                            const userData= await searchUser(record.id);  
                            setVisible(true);                           
                            setUserData(userData);                                                    
                        }}/>                       
                    </Tooltip>                  
                    <Popconfirm 
                        placement="left" 
                        title={t('common.deleteConfirm')}
                        okText={t('common.okText')}
                        cancelText={t('common.cancelText')}
                        onConfirm={async()=>{
                            await dropUser(record.id);
                            window.location.reload();                            
                        }}>
                        <Tooltip title={t('common.deleteAction')}>
                            <Button type="text" icon={<DeleteOutlined />} />
                        </Tooltip>
                    </Popconfirm>
                </Space>
            )
        }
    ];


  
    
    const fetchData = async (params: any) => {
        setIsLoading(true);
        setData([]);
        const userData = await fetchUsers(params);
        // if(userData?.code === 200 && userData?.data?.list){
        //     setIsEmpty(false);
        //     setData(userData.data.list);
        //     setPageParams({
        //         total: userData.data.total,
        //         pageSize: userData.data.pageSize,
        //         current: userData.data.page
        //     });
        // }

        if(userData?.code === 200 && userData.data.records){
            setIsEmpty(false);
            setData(userData.data.records);
            setPageParams({
                total: userData.data.total,
                pageSize: userData.data.size,
                current: userData.data.current
            });
        }
        setIsLoading(false);
    }
    const [refresh, setrRefresh] = useState(0);  
    const [visible, setVisible] = useState(false);  
    const [confirmLoading, setConfirmLoading] = useState(false);   
    useEffect(() => {
        PubSub.subscribe('refresh', (msg,data)=>{                    
            setrRefresh(refresh+1);
            setVisible(false); 
        }); 
        fetchData({
            ...pageParams
        });
        
    }, [refresh]);//eslint-disable-line
   
    

    const showModal = () => { 
        setVisible(true); 
        setUserData(undefined);      
    };   

    // const handleOk = () => {
    //     setVisible(false);
    //     setConfirmLoading(true);
    //     setTimeout(() => {
    //         setVisible(false);
    //         setConfirmLoading(false);
    //     }, 2000);
    // };
  
    const handleCancel = () => {
        setVisible(false);
    };  

  
    return(
        <PageLayout title={ t('users.PageTitle') } subTitle={ t('users.PageSubTitle') }>
            <Row className="page-action-row">              
                <Button type={ "dashed" } icon={ <PlusOutlined/> } onClick={showModal}>{t('common.addAction')}</Button>              
                <Modal title={ t('userinfo.title') }
                     visible={visible}
                     //onOk={handleOk}
                     onCancel={handleCancel}
                     confirmLoading={confirmLoading}
                     footer={[<Button key="back" onClick={handleCancel}>{t('common.returnText')}</Button>]}>                     
                     <AddUser userData={userData}/>
                </Modal>
            </Row>
            <ConfigProvider renderEmpty={ isEmpty ? () => <Empty description={t('common.EmptyDescription')}/> : undefined }>
                <Table
                    rowKey={ (record: UserItemDataType) => record.id.toString() } 
                    className="page-data-table"
                    columns={ columns() }
                    dataSource={ data }
                    loading={ isLoading }
                    pagination={{ 
                        position: ["bottomRight"], 
                        total: pageParams.total,
                        showSizeChanger: true,
                        showQuickJumper: true,
                        current: pageParams.current,
                        pageSize: pageParams.pageSize, 
                        onChange: (page, pageSize) => {                       
                            const Params = {
                                total: pageParams.total,
                                pageSize: pageSize,        
                                current:page
                            }
                            fetchData(Params);
                        } 
                    }}
                    scroll={{ y: 'auto' }}
                    expandable={{
                        expandedRowRender: record => <p style={{ margin: 0 }}>
                            <Tooltip title={`${record.loginName} ( ${record.chineseName} | ${record.englishName} )`}>
                             <Avatar src={record.avatar} />
                            </Tooltip>
                            <DrawCanvas h={100} w={100}/>
                            </p>,
                    }}
                    // onChange={ (pagination: any) => {
                        // setPageParams({
                        //     total: pagination.total,
                        //     pageSize: pagination.pageSize,                                                  
                        //     current: pagination.current
                        // });
                        // const Params = {
                        //     total: pagination.total,
                        //     pageSize: pagination.pageSize,        
                        //     current: pagination.current
                        // }
                        // fetchData(Params);
                    // } }
                />
            </ConfigProvider>
        </PageLayout>
    );
}

export default UsersManagementPage;