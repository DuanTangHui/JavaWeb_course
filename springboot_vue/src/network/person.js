import { request } from '@/network/request'

export function getPersonInfoByID(params) {
    return request({
        url: '/PerInfo/getPersonInfo',
        method: 'get',
        params: params,
    })
}
export function updatePersonalInfo(data){
    return request({
        url:'/PerInfo/updatePersonalInfo',
        method: 'post',
        data: data,
    })
}

export function getHistoryById(params) {
    return request({
        url: '/PerInfo/getHistory',
        method: 'get',
        params: params,
    })
}
export function submitPersonInfo(data){
    return request({
        url:'/PerInfo/submitPersonInfo',
        method: 'post',
        data: data,
    })
}
