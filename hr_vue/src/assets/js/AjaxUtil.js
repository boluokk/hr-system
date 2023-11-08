import axios from 'axios'
import { Message } from 'element-ui'
import Router from '../../router/index.js'
import store from '../../store/index'

axios.interceptors.request.use(
  config => {
    return config
  },
  err => {
    Message.error({ message: '请求超时!' })
    return Promise.resolve(err)
  }
)
let flushTime
let interval = 1500
axios.interceptors.response.use(
  data => {
    if (data.data.status && data.data.status !== 200) {
      if (flushTime && Date.now() - flushTime < interval) {
        return
      }
      Message.error({ message: data.data.msg })
      flushTime = Date.now()
      return Promise.resolve(data)
    }
    return data
  },
  err => {
    if (err.response.status === 504) {
      Message.error({ message: '服务器被吃了⊙﹏⊙∥' })
    } else if (err.response.status === 403) {
      if (store.state.msgCount === 0) {
        Message.error({ message: '权限不足,请联系管理员!' })
        store.commit('addMsgCount')
      }
    } else if (err.response.status === 402) {
      Message.error('账号有其他人登录, 请修改密码')
      Router.replace('/')
    } else if (err.response.status === 401) {
      Message.error({ message: '请重新登录' })
      Router.replace('/')
    } else if (err.response.status === 404) {
      Message.error({ message: '未知请求路径' })
    } else {
      Message.error({ message: '未知错误!' })
    }
    return Promise.resolve(err)
  }
)

// 统一前缀
const base = ''

export const postRequest = (url, params) => {
  let ans = {}
  for (let it in params) {
    if (params[it] !== undefined || params[it] !== null) {
      ans[it] = params[it]
    }
  }
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: ans,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
export const putRequest = (url, params) => {
  let ans = {}
  for (let it in params) {
    if (params[it] !== undefined || params[it] !== null) {
      ans[it] = params[it]
    }
  }
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: ans,
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
export const deleteRequest = (url, parmas) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    data: parmas
  })
}
export const getRequest = url => {
  return axios({
    method: 'get',
    url: `${base}${url}`
  })
}
export const downloadFiles = (url, formData, fullFileName) => {
  axios({
    method: 'post',
    url: `${base}${url}`,
    data: formData,
    responseType: 'blob',
    headers: {}
  })
    .then(response => {
      const url = window.URL.createObjectURL(new Blob([response.data]))
      const a = document.createElement('a')
      a.style.display = 'none'
      a.href = url
      a.download = fullFileName || '报表.xlsx'
      document.body.appendChild(a)
      a.click()
      window.URL.revokeObjectURL(url)
    })
    .catch(error => {
      console.error('下载失败: ' + error)
    })
}
export const uploadFiles = (url, file) => {
  let formData = new FormData()
  formData.append('file', file.file)
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}
