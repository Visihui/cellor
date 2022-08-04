import { FC, Suspense, useState } from "react";
import { Avatar, Badge, Col, Drawer, Input, Layout, Row, Tooltip } from "antd";
import { SearchOutlined, UserOutlined, NotificationOutlined } from "@ant-design/icons";
import './dashboard.less';
import { 
    BrowserRouter as Router, Route, Routes, Navigate
} from "react-router-dom";
import Logo from '@/assets/logo.svg';
import { getUserInfo } from "../../utils/LocalStorageUtil";
import { useTranslation } from "react-i18next";
import React from "react";
import Loading from "../../components/loading/Loading";
import AppMenu from "../../components/menu/Menu";

const { Sider, Header, Content } = Layout;

const UsersManagementPage = React.lazy(() => import('./subpages/UsersManagementPage'));

const ApplicationManagementPage = React.lazy(() => import('./applicationpages/ApplicationManagementPage'));

const DashboardContent: FC = () => {
    const userInfo = getUserInfo();
    const [visible, setVisible] = useState(false);
    const { t } = useTranslation();
    const closeDrawer = () => {
        setVisible(false);
    };

    return(
        <Layout className="dashboard-container">
            <Header className="dashboard-container-header" >
                <Row gutter={16} className="header-container">
                    <Col span={1}>
                        <img src={Logo} alt="logo" className="header-logo"/>
                    </Col>
                    <Col span={2}>
                        <h3> Cellor SSO </h3>
                    </Col>
                    <Col span={5}>
                        <Input placeholder={ t('common.Search') } prefix={ <SearchOutlined/> }/>
                    </Col>
                    <Col span={16}>
                        <Row justify="end" align="middle" gutter={16}>
                            <Col>
                                <Badge count={3} size="small">
                                    <NotificationOutlined onClick={()=>{
                                        setVisible(true);
                                    }}/>
                                </Badge>
                            </Col>
                            <Col>
                                <Tooltip title={ userInfo?.name }>
                                    <Avatar src={ userInfo && userInfo.avatar ? userInfo.avatar: <UserOutlined/> }/>
                                </Tooltip>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </Header>
            <Layout>
                <Sider className="dashboard-container-sider" >
                    <AppMenu />
                </Sider>
                <Layout>
                    <Content>
                        <Routes>
                            <Route path="" element={ <Navigate to="management" replace/> } />
                            <Route path="document" />
                            <Route path="management">
                                <Route path="" element={ <Navigate to="./users" replace/> } />
                                <Route path="users/*" element={ <Suspense fallback={ <Loading/> }> <UsersManagementPage /></Suspense> } />
                                <Route path="" element={ <Navigate to="./applications" replace/> } />
                                <Route path="applications/*" element={<Suspense fallback={ <Loading/> }> <ApplicationManagementPage /></Suspense> } />
                                <Route path="*" element={<Navigate to="/404" />} />
                            </Route>
                            <Route path="*" element={<Navigate to="/404" />} />
                        </Routes>
                    </Content>
                </Layout>
            </Layout>
            <Drawer
                title={t('notification.title')}
                placement="right"
                visible={visible}
                onClose={closeDrawer}>

            </Drawer>
        </Layout>
    );
}

const Dashboard: FC = () => {
    
    return(
        <Router>
            <Routes>
                <Route path="" element={<Navigate to="/home" />} />
                <Route path="home/*" element={ <DashboardContent /> } />
                <Route path="*" element={<Navigate to="/404" />} />
                <Route path="404" element={ <div>404</div>} />
            </Routes>
        </Router>
    );
}

export default Dashboard;