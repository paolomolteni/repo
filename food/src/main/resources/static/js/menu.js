const routes = [
  { path: '/person', component: person },
]

const router = new VueRouter({
	routes: routes
})

const app = new Vue({
  router
}).$mount('#menu')