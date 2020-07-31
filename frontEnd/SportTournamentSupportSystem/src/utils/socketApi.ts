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
  data: IParams = {},
  params: IParams = {},
  path: string | number,
) {
  const baseUrl = 'http://192.168.43.170:8090/';
  const realUrl = `${baseUrl}${uri}`;

  return new Promise<IResponse<T>>((resolve: Function, reject: Function) => {
    switch (method) {
      case METHOD.POST: {
        axios.post(`${realUrl}${path !== '' ? `/${path}` : ''}`, null, { params, data })
          .then((response) => {
            console.log('response1', response);
            resolve(response);
          }).catch((error) => {
            console.log('error1', error);
            reject(error);
          });
        break;
      }
      case METHOD.PUT: {

        break;
      }
      case METHOD.GET: {
        axios.get(`${realUrl}${path !== '' ? `/${path}` : ''}`, { params, data })
          // axios.get(`${realUrl}/${params.id}`)
          .then((response) => {
            console.log('response1', response);
            resolve(response);
          }).catch((error) => {
            console.log('error1', error);
            reject(error);
          });
        break;
      }
      case METHOD.DELETE: {

        break;
      }
      default: {
        break;
      }
    }
  });
}