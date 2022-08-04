interface AppStateType{
    state:number;
}

const AppState: AppStateType = {
    state:0
}

const processState = (state = AppState, action:any) => {
    switch(action.type){
        default:
            return state;
    }
}

export default processState;