import { request } from '@/network/request'

export function login(params) {
    return request({
        url: '/login',
        method: 'get',
        params: params,
    })
}