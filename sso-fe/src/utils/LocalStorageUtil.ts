import { UserInfoType } from '../api/UserApi';

const storage = window.localStorage;

const getUserInfo = (): UserInfoType | undefined => {
    const userStr = storage.getItem('userinfo');
    if (userStr) {
        return JSON.parse(userStr);
    }
    return undefined;
}

const setUserInfo = (user: UserInfoType): void => {
    storage.setItem('userinfo', JSON.stringify(user));
}

const removeUserInfo = (): void => {
    storage.removeItem('userinfo');
}

const getTicket = (): string | undefined => {
    const ticket = storage.getItem('ticket');
    if (ticket) {
        return ticket;
    }
    return undefined;
}

const setTicket = (ticket: string): void => {
    storage.setItem('ticket', ticket);
}

const removeTicket = (): void => {
    storage.removeItem('ticket');
}

export{
    getUserInfo,
    setUserInfo,
    removeUserInfo,
    getTicket,
    setTicket,
    removeTicket
}