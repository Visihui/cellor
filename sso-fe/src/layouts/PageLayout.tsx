import React, { FC } from 'react';
import { PageHeader, Row, Breadcrumb } from 'antd';
import { useLocation, useNavigate } from 'react-router-dom';
import './PageLayout.less';

interface PageLayoutProps{
    title: string|undefined;
    subTitle: string|undefined;
    children: React.ReactNode;
}

interface PageProps{
    title: string|undefined;
    subTitle: string|undefined;
}

const PageLayout: FC<PageLayoutProps> = (props) => {

    const location = useLocation();
    const navigate = useNavigate();
    const parseLocation = (location: any) => {
        const path = location.pathname.split('/');
        const breadcrumb: Array<React.ReactNode> = [];
        path.forEach((item: string, index: number) => {
            if(item){
                breadcrumb.push(
                    <Breadcrumb.Item key={index}>
                        { item }
                    </Breadcrumb.Item>
                );
            }
        });
        return breadcrumb;
    }
    return(
        <div className="page-container">
            <Row>
                <Breadcrumb>
                    {parseLocation(location)}
                </Breadcrumb>
            </Row>
            <Row>
                <PageHeader
                    onBack={() => { 
                        navigate(-1);
                     }}
                    title={props.title}
                    subTitle={props.subTitle}
                />
            </Row>
            <Row className='page-content'>
                {props.children}
            </Row>
        </div>
    );
}

export default PageLayout;
export type { PageLayoutProps, PageProps };