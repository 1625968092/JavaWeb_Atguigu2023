import {createRouter,createWebHashHistory} from 'vue-router'


import Login from '../components/Login.vue'
import Regist from '../components/Regist.vue'
import ShowSchedule from '../components/ShowSchedule.vue'

import pinia from '../pinia.js'

import {defineUser} from '../store/userStore.js'

let sysUser = defineUser(pinia)


const router=createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:"/",
            redirect:'/showSchedule'
        },
        {
            path:"/showSchedule",
            component:ShowSchedule
        },
        {
            path:"/login",
            component:Login
        },
        {
            path:"/regist",
            component:Regist
        }
    ]
})


//通过路由的全局前置守卫判断是否放行showSchedule
router.beforeEach((to,from,next)=>{
    if(to.path=='/showSchedule'){
        //没登录回到登录页
        if("" == sysUser.username){
            next("/login")
        }else{
            //登陆过放行
            next()
        }
        
    }else{
        next()
    }
})


export default router