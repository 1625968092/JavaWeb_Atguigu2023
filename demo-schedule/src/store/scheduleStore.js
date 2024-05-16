/**
 * 专门用于存放日程状态的pinia
 */
import {defineStore} from 'pinia'

export const defineSchedule=defineStore(
    "scheduleList",
    {
        state:()=>{
            return {
                itemList:[
                    {
                        sid:1,
                        uid:1,
                        title:"学习Php",
                        completed:1
                    },
                    {
                        sid:1,
                        uid:1,
                        title:"学习Java",
                        completed:0
                    },
                    {
                        sid:1,
                        uid:1,
                        title:"学习Java",
                        completed:0
                    },
                ],

            }
        },
        getters:{
            
        },
        actions:{

        }
    }
)