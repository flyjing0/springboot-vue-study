import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import 'dayjs/locale/zh-cn'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import '@vant/touch-emulator';

import '@/assets/css/global.css'//统一样式
//import { TreeSelect  } from 'vant';
//import { Button  } from 'vant';
//import { ConfigProvider } from 'vant';
//import { Search } from 'vant';
//import { Popup } from 'vant';
//import { Form, Field, CellGroup } from 'vant';
import Vant from 'vant';

createApp(App).use(Vant).use(router).use(ElementPlus, { locale ,size:'small'}).mount('#app')
