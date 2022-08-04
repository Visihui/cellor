import React,{ useEffect } from "react";
import { useTranslation } from "react-i18next";
import PubSub from 'pubsub-js' ;
import { Input, Select, Button, Form } from "antd";
import { createApplication,editApplication } from '../../../services/ApplicationService';
import { ApplicationInfoType } from "../../../api/ApplicationApi";

const { Option } = Select;
const { TextArea } = Input;
const formlayout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 18 },
};

interface applicationPropsType{
    applicationData: ApplicationInfoType|undefined 
  }
    const AddApplication= (props:applicationPropsType) => {
    const { t } = useTranslation();  
    const [userForm] = Form.useForm();  
    // const [applicationTypeData,setApplicationTypeData]=useState<ApplicationTypeList|undefined>();
    // const InitData = async () => {      
    //     const applicationTypeDataList =  await searchApplicationTypeList();             
    //     if(applicationTypeDataList?.code === 200 && applicationTypeDataList.data){           
    //         setApplicationTypeData(applicationTypeDataList.data);           
    //     }       
    // }
   
    const handleChange = () => {
        // console.log(`selected ${value}`);
    };   
   
    useEffect(() => {
            // InitData();
            userForm.resetFields(undefined);
            userForm.setFieldsValue(props.applicationData);               
        }, [props.applicationData])
    return(
        <div> 
        <Form {...formlayout} 
                    initialValues={{ remember: true }} 
                    form = {userForm} 
                    onFinish = {async ( value )=> {                       
                        value.id=props.applicationData?.id;
                         if(props.applicationData?.id)
                         {
                            await editApplication(value);         
                         }                          
                        else{
                            await createApplication(value); 
                         }                   
                          //window.location.reload();
                          PubSub.publish('refreshApplication', 'refreshApplication');  
                     }}>
                      <Form.Item                                   
                        name="id" 
                        hidden={true}                       
                       >    
                          <Input hidden={true}  />
                    </Form.Item>                   
                    <Form.Item 
                        label={t('applicationInfo.applicationName')}                      
                        name="applicationName"
                        rules={[{required:true, message: t('applicationInfo.applicationNamePlaceholder')}]}>
                        <Input  placeholder={t('applicationInfo.applicationNamePlaceholder')} maxLength={20}/>
                    </Form.Item>                  
                  
                    <Form.Item 
                        label={t('applicationInfo.typeName')}                         
                        name="typeCode"
                        rules={[{required:true, message: t('applicationInfo.typeNamePlaceholder')}]}>                      
                        <Select  style={{ width: 120 }} onChange={handleChange} placeholder={t('applicationInfo.typeNamePlaceholder')} >                         
                            <Option value="1">{t('applicationInfo.typeCode.weChatCode')} </Option>
                            <Option value="2">{t('applicationInfo.typeCode.androidCode')} </Option>
                            <Option value="3">{t('applicationInfo.typeCode.iosCode')} </Option>
                            <Option value="4">{t('applicationInfo.typeCode.webCode')} </Option>                            
                        </Select>
                    </Form.Item> 
                    <Form.Item 
                        label={t('applicationInfo.applicationDescribe')}                        
                        name="applicationDescribe"
                        rules={[{required:true, message: t('applicationInfo.applicationDescribePlaceholder')}]}>                      
                        <TextArea rows={8} placeholder={t('applicationInfo.applicationDescribePlaceholder')} maxLength={200}/>
                    </Form.Item>                             
                    <Form.Item
                        wrapperCol={{ span: 18, offset: 6 }}>
                        <Button type="primary" htmlType="submit">{t('common.okText')}</Button>                         
                    </Form.Item>
                </Form>
        </div>
 
    );
}
export default AddApplication