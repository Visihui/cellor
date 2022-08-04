import { Result } from "antd";
import React,{ FC } from "react";
import { useTranslation } from "react-i18next";
import './Error.less';


const Error: FC = () => {
    const { t } = useTranslation();

    return (
        <div className={'center-content'}>
        <Result
            status='error'
            title={ t('common.loadingError') }
            subTitle={ t('error.failedLoadPage') }
            />
        </div>
    );
}

export default Error;