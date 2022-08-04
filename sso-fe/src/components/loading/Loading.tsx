import { Spin } from "antd";
import React,{ FC } from "react";
import { useTranslation } from "react-i18next";
import './Loading.less';

const Loading: FC = () => {
    const { t } = useTranslation();

    return (
        <div className={'center-content'}>
            <Spin size='large' tip={ t('common.loading') }/>
        </div>
    );
};

export default Loading;