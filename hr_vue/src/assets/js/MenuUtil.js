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
