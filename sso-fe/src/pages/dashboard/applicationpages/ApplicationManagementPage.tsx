import React,{ FC, useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import PubSub from 'pubsub-js' ;
import PageLayout from "../../../layouts/PageLayout";
import { Empty, Table, ConfigProvider, Tooltip, Row, Button, Space, Popconfirm,Modal,Input } from "antd";
import { PlusOutlined, FormOutlined, DeleteOutlined } from "@ant-design/icons";
import { ColumnsType } from "antd/lib/table";
import { ApplicationInfoType,ApplicationItemDataType } from "../../../api/ApplicationApi";
import { dropApplication, searchApplication} from "../../../services/ApplicationService";
import AddApplication from "./AddApplication";
import { getApplicationList } from "../../../api/ApplicationApi";

const ApplicationManagementPage:FC=()=>{
    const initPageParams = {
        total: 1,
        pageSize: 10,        
        current: 1
    }
    const [ data, setData ] = useState<ApplicationItemDataType[]>([]);  
    const [ pageParams, setPageParams ] = useState(initPageParams);
    const [ isLoading, setIsLoading ] = useState<boolean>(false);
    const [ isEmpty, setIsEmpty ] = useState(true);
    const { t } = useTranslation(); 
    const [ applicationData, setApplicationData ] = useState<ApplicationInfoType|undefined>();   

    const columns: () => ColumnsType<ApplicationItemDataType> = () => [
        Table.EXPAND_COLUMN,
     
        {
            title: t('applications.columns.applicationName'),
            dataIndex: "applicationName",
            key: "applicationName",
            align: "center",
        },      
        {
            title: t('applications.columns.typeName'),
            dataIndex: "typeName",
            key: "typeName",
            align: "center",
        },
        {
            title: t('applications.columns.appKey'),
            dataIndex: "appKey",
            key: "appKey",
            align: "center",
            ellipsis:true,        
            
        },
        {
            title: t('common.columns.createdAt'),
            dataIndex: "createTime",
            key: "createAt",
            align: "center"
        },
        {
            title: t('common.columns.updatedAt'),
            dataIndex: "updateTime",
            key: "updateAt",
            align: "center"
        },
        {
            title: t('common.columns.actions'),
            key: "actions",
            align: "center",
            render: (_, record: ApplicationItemDataType) => (
                <Space size="middle">
                    <Tooltip title={t('common.updateAction')}>
                        <Button type="text" icon={<FormOutlined />} onClick={async () => {                       
                        const applicationData= await searchApplication(record.id);  
                            setVisible(true);                           
                            setApplicationData(applicationData);                                                    
                        }}/>                       
                    </Tooltip>                  
                    <Popconfirm 
                        placement="left" 
                        title={t('common.deleteConfirm')}
                        okText={t('common.okText')}
                        cancelText={t('common.cancelText')}
                        onConfirm={async()=>{
                            await dropApplication(record.id);
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
        const applicationData =  await getApplicationList(params);             
        if(applicationData?.code === 200 && applicationData.data.records){
            setIsEmpty(false);
            setData(applicationData.data.records);
            setPageParams({
                total: applicationData.data.total,
                pageSize: applicationData.data.size,
                current: applicationData.data.current
            });
        }
        setIsLoading(false);
    }
    const [refreshApplication, setRefreshApplication] = useState(0);  
    const [visible, setVisible] = useState(false);  
    const [confirmLoading, setConfirmLoading] = useState(false); 
    useEffect(() => {
        PubSub.subscribe('refreshApplication', (msg,data)=>{                    
            setRefreshApplication(refreshApplication+1);
            setVisible(false);
        }); 
        fetchData({
            ...pageParams
        });
    }, [refreshApplication]);//eslint-disable-line 

    

    const showModal = () => { 
        setVisible(true);  
        setApplicationData(undefined);           
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
        <PageLayout title={ t('applications.PageTitle') } subTitle={ t('applications.PageSubTitle') }>
            <Row className="page-action-row">              
                <Button type={ "dashed" } icon={ <PlusOutlined/> } onClick={showModal}>{t('common.addAction')}</Button>
                <Modal title={ t('applicationInfo.title') }
                    visible={visible}
                    // onOk={handleOk}
                    onCancel={handleCancel}
                    confirmLoading={confirmLoading} 
                    footer={[<Button key="back" onClick={handleCancel}>{t('common.returnText')}</Button>]}>                     
                    <AddApplication applicationData={applicationData}/>
                </Modal>
            </Row>
            <ConfigProvider renderEmpty={ isEmpty ? () => <Empty description={t('common.EmptyDescription')}/> : undefined }>
                <Table
                
                    rowKey={ (record: ApplicationItemDataType) => record.id.toString() } 
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
                    // expandable={{
                    //     expandedRowRender: record => <p style={{ margin: 0 }}>
                    //         </p>,
                    // }}
                   
                />
            </ConfigProvider>
        </PageLayout>
    );
}
export default ApplicationManagementPage;