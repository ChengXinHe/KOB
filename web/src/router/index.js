import { createRouter, createWebHistory } from 'vue-router'
import PkindexView from '../views/pk/PkindexView'
import RanklistindexView from '../views/ranklist/RanklistindexView'
import RecordindexView from '../views/record/RecordindexView'
import UserbotsindexView from '../views/user/bots/UserbotsindexView'
import NotFound from '../views/error/NotFound'
import UserAccountLoginViewVue from '@/views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterViewVue from '@/views/user/account/UserAccountRegisterView.vue'
import store from '../store/index'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/pk/",
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/pk/",
    name: "pk_index",
    component: PkindexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/ranklist/",
    name: "ranklist_index",
    component: RanklistindexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/record/",
    name: "record_index",
    component: RecordindexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/bots/",
    name: "user_bots_index",
    component: UserbotsindexView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  },
  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginViewVue,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterViewVue,
    meta: {
      requestAuth: false,
    }
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => { //在router起作用之前执行的函数
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "user_account_login"})
  } else {
    next();
  }
})

export default router
