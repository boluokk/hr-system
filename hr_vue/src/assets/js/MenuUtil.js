import { getRequest } from '../js/AjaxUtil'

export const initMenu = (router, store) => {
  if (store.state.routes.length > 0) {
    return
  }

  getRequest('/syscon/menu').then(data => {
    const res = data.data
    if (res) {
      const fmtRoutes = formatRoutes(res)
      router.addRoutes(fmtRoutes)
      store.commit('initeMenu', fmtRoutes)
      // store.dispatch('connect');
    }
  })
}

export const formatRoutes = routes => {
  // console.log(routes)
  const ResultArr = []
  routes.forEach(router => {
    let { path, component, name, meta, iconClass, children } = router
    if (children && children instanceof Array) {
      children = formatRoutes(children)
    }
    const formtRo = {
      path: path,
      name: name,
      meta: meta,
      iconcls: iconClass,
      children: children,
      component(resolve) {
        if (component.startsWith('Home')) {
          require(['../../components/views/' + component + '.vue'], resolve)
        } else if (component.startsWith('Emp')) {
          require(['../../components/views/emp/' + component + '.vue'], resolve)
        } else if (component.startsWith('Per')) {
          require(['../../components/views/per/' + component + '.vue'], resolve)
        } else if (component.startsWith('Sal')) {
          require(['../../components/views/sal/' + component + '.vue'], resolve)
        } else if (component.startsWith('Sta')) {
          require(['../../components/views/sta/' + component + '.vue'], resolve)
        } else if (component.startsWith('Sys')) {
          require(['../../components/views/sys/' + component + '.vue'], resolve)
        }
      }
    }
    ResultArr.push(formtRo)
  })
  return ResultArr
}

export const formatDate = (date) => {
  return formatDateTime(date, 'yyyy-MM-dd HH:mm:ss')
}
const formatDateTime = function
  (date, format) {
  const o = {
    'M+': date.getMonth() + 1, // 月份
    'd+': date.getDate(), // 日
    'h+': date.getHours() % 12 === 0 ? 12 : date.getHours() % 12, // 小时
    'H+': date.getHours(), // 小时
    'm+': date.getMinutes(), // 分
    's+': date.getSeconds(), // 秒
    'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
    S: date.getMilliseconds(), // 毫秒
    a: date.getHours() < 12 ? '上午' : '下午', // 上午/下午
    A: date.getHours() < 12 ? 'AM' : 'PM' // AM/PM
  }
  if (/(y+)/.test(format)) {
    format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (let k in o) {
    if (new RegExp('(' + k + ')').test(format)) {
      format = format.replace(
        RegExp.$1,
        RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length)
      )
    }
  }
  return format
}
