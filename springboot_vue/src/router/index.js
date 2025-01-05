import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/LoginWelcome/Login'
import store from '../store'

Vue.use(VueRouter)

//解决编程式路由往同一地址跳转时会报错的情况
const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;
//push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch(err => err);
};
//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalReplace.call(this, location, onResolve, onReject);
  return originalReplace.call(this, location).catch(err => err);
};

let routes;
routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    meta: {
      title: '每日健康登记管理系统--登录页面',
      requireAuth: false,
    },
  },
  {
    path: '/Home',
    name: 'Home',
    component: () => import('../views/Home'),
    meta: {
      requireAuth: true,
    },
    children: [
      {
        path: '/registerInfo',
        name: 'registerInfo',
        component: () => import(/* webpackChunkName: "about" */ '../views/controller/registerInfo'),
        meta: { title: '所有人员登记信息', requireAuth: true, },
      },
      {
        path: '/unRegisterInfo',
        name: 'unRegisterInfo',
        component: () => import(/* webpackChunkName: "about" */ '../views/controller/unRegisterInfo'),
        meta: { title: '所有未登记人员信息', requireAuth: true, },
      },
      {
        path: '/userManage',
        name: 'userManage',
        component: () => import(/* webpackChunkName: "about" */ '../views/controller/userManage'),
        meta: { title: '用户管理', requireAuth: true, },
      },
      {
        path: '/historyRegister',
        name: 'historyRegister',
        component: () => import(/* webpackChunkName: "about" */ '../views/normal/historyRegister'),
        meta: { title: '历史登记信息', requireAuth: true, },
      },
      {
        path: '/nowRegister',
        name: 'nowRegister',
        component: () => import(/* webpackChunkName: "about" */ '../views/normal/nowRegister'),
        meta: { title: '当前登记信息', requireAuth: true, },
      },
      {
        path: '/personalInfo',
        name: 'personalInfo',
        component: () => import(/* webpackChunkName: "about" */ '../views/normal/personalInfo'),
        meta: { title: '个人信息', requireAuth: true, },
      }
    ]
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
// 路由守卫钩子函数 判断登录的response
router.beforeEach((to, _from, next) => {
  let isLogin = store.getters.getUserName;
  if (to.meta.requireAuth) { // 校验路由，除了登录之外的页面都要校验
    if (isLogin != '?') { // 是否有登录过
        next()
    } else {
      alert('密码错误或未登录'); // 未登录或密码错误，跳转到login页面
      next({
        path: '/',
        query: {redirect :to.fullPath}
      })
    }
  } else { // 如果是 login 路由
    next();
  }
})
router.afterEach((to) => {
  document.title = to.meta.title;
})
export default router
