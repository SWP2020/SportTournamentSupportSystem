import axios from 'axios';
import { IParams, IResponse } from "interfaces/common";

export enum METHOD {
  GET = 'get',
  POST = 'post',
  PUT = 'put',
  DELETE = 'delete',
}

export async function query<T>(
  uri: string,
  method: METHOD,
  params: IParams = {}
) {
  const baseUrl = 'http://localhost:8081/';
  const realUrl = `${baseUrl}${uri}`;


  return new Promise<IResponse<T>>((resolve: Function, reject: Function) => {
    axios.get(realUrl, params)
      .then((response) => {
        console.log('response', response);
        resolve(response);
      }).catch((error) => {
        console.log('error', error);
        reject(error);
      });

  });
}