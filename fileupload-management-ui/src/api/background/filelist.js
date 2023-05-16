import request from '@/utils/request'

// 查询已上传文件列表列表
export function listFilelist(query) {
  return request({
    url: '/background/filelist/list',
    method: 'get',
    params: query
  })
}

// 查询已上传文件列表详细
export function getFilelist(id) {
  return request({
    url: '/background/filelist/' + id,
    method: 'get'
  })
}

// 新增已上传文件列表
export function addFilelist(data) {
  return request({
    url: '/background/filelist',
    method: 'post',
    data: data
  })
}

// 修改已上传文件列表
export function updateFilelist(data) {
  return request({
    url: '/background/filelist',
    method: 'put',
    data: data
  })
}

// 删除已上传文件列表
export function delFilelist(id) {
  return request({
    url: '/background/filelist/' + id,
    method: 'delete'
  })
}

// 导出已上传文件列表
export function exportFilelist(query) {
  return request({
    url: '/background/filelist/export',
    method: 'get',
    params: query
  })
}