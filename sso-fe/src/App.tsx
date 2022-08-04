import React,{ FC, useEffect, useState } from 'react';
import { checkLogin } from './services/LoginService';
import LoginPage from './pages/login/LoginPage';
import Dashboard from './pages/dashboard/dashboard';
import './App.less';
import Loading from './components/loading/Loading';
import Error from './components/error/Error';

const App: FC = ()=> {
  const [isLogin, setIsLogin] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [isError, setIsError] = useState(false);

  const checkLoginStatus = () => {
    setIsLoading(true);
    checkLogin().then(res=> {
      if(res){
        setIsLogin(true);
      }else{
        setIsLogin(false);
      }
    }).catch(err=> {
      setIsError(true);
    }).finally(()=> {
      setIsLoading(false);
    });
  }

  useEffect(()=> {
    checkLoginStatus();
  }, []);

  if(isLoading){
    return (
      <Loading />
    );
  }else if( isError ){
    return(
      <Error />
    );
  }else{
    return(
      isLogin ? <Dashboard /> : <LoginPage />
    )
  }
}

export default App;
