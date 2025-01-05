import { request } from '@/network/request'

export function getAllStuInfo(page,num) {
  return request({
    url: '/StuInfo/findAll/'+page+'/'+num,
    method: 'get',
    // params: params,
  })
}

export function addStuInfo(data) {
  return request({
    url: '/StuInfo/addStu',
    method: 'post',
    data: data,
  })
}

export function updateStuInfo(data) {
  return request({
    url: '/StuInfo/updateStu',
    method: 'post',
    data: data,
  })
}
export function changeIsUse(data) {
  return request({
    url: '/StuInfo/changeIsUse',
    method: 'post',
    data: data,
  })
}
export function searchStuInfo(params) {
  return request({
    url: '/StuInfo/serachStuByIdOrStatus',
    method: 'get',
    params: params,
  })
}
