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
  const baseUrl = 'http://10.22.177.194:8090/';
  const realUrl = `${baseUrl}${uri}`;

  return new Promise<IResponse<T>>((resolve: Function, reject: Function) => {
    switch (method) {
      case METHOD.POST: {
        axios.post(realUrl, params)
          .then((response) => {
            console.log('response', response);
            resolve(response);
          }).catch((error) => {
            console.log('error', error);
            reject(error);
          });
        break;
      }
      case METHOD.PUT: {

        break;
      }
      case METHOD.GET: {

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