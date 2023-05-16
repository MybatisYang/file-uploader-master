import request from '@/utils/request'

// 查询测试信息列表
export function listImmortal(query) {
  return request({
    url: '/system/immortal/list',
    method: 'get',
    params: query
  })
}

// 查询测试信息详细
export function getImmortal(id) {
  return request({
    url: '/system/immortal/' + id,
    method: 'get'
  })
}

// 新增测试信息
export function addImmortal(data) {
  return request({
    url: '/system/immortal',
    method: 'post',
    data: data
  })
}

// 修改测试信息
export function updateImmortal(data) {
  return request({
    url: '/system/immortal',
    method: 'put',
    data: data
  })
}

// 删除测试信息
export function delImmortal(id) {
  return request({
    url: '/system/immortal/' + id,
    method: 'delete'
  })
}

// 导出测试信息
export function exportImmortal(query) {
  return request({
    url: '/system/immortal/export',
    method: 'get',
    params: query
  })
}