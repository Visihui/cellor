interface ResponseData<T> {
    code: number;
    msg: string;
    data: T;
}

export type { ResponseData };

