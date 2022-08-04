import React,{ useState } from "react";
import { Upload,message } from 'antd';
import ImgCrop from  'antd-img-crop';
import type { RcFile, UploadFile, UploadProps } from 'antd/es/upload/interface';

interface AvatarPropsType{
  // avatarFile: string|undefined
  // avatarChange:(file:UploadFile<any>)=>UploadFile<any>|undefined
  avatarChange:(filestr:string|undefined)=>string|undefined;
}

const AvatarLoad = (props: AvatarPropsType) => {
  const [fileList, setFileList] = useState<UploadFile[]>([  
  ]); 

  const onChange: UploadProps['onChange'] = ({ fileList: newFileList }) => {
    setFileList(newFileList);
    if(fileList && fileList.length > 0){
      const file =fileList[0];
     props.avatarChange(file.thumbUrl)
    }
      // createAvatar(file.thumbUrl?.toString());     
  };

  const onPreview = async (file: UploadFile) => {
    let src = file.url as string;
    if (!src) {
      src = await new Promise(resolve => {
        const reader = new FileReader();
        reader.readAsDataURL(file.originFileObj as RcFile);
        reader.onload = () => resolve(reader.result as string);
      });
    }
    const image = new Image();
    image.src = src;
    const imgWindow = window.open(src);
    imgWindow?.document.write(image.outerHTML);
  };
const beforeUpload = (file: RcFile) => { 
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('Image must smaller than 64MB!');
  }
  return  isLt2M;
};
 
  return (
    <ImgCrop rotate >
      <Upload
        // action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
        listType="picture-card"
        fileList={fileList}
        onChange={onChange}
        onPreview={onPreview} 
        beforeUpload={beforeUpload}     
      >
        {fileList.length < 1 && '+ Upload'}
      </Upload>
    </ImgCrop>
  );
};
export default AvatarLoad;