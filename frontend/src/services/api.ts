import axios, { AxiosInstance } from 'axios';

export const api: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8081'
});

export const apiIA: AxiosInstance = axios.create({
  baseURL: 'http://localhost:8080'
});
