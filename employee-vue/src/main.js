import Vue from 'vue'
import App from './App.vue'
import van from 'vant';
import router from './router'
import 'vant/lib/index.css';

Vue.config.productionTip = false
Vue.use(van)

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
