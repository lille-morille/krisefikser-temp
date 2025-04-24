import './assets/globals.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

router.beforeEach((to, _, next) => {
  // TODO: Check JWT validity
  const isAuthenticated = false

  if (['sign-in', 'sign-up', 'forgot-password'].includes(to.name as string)) {
    // TODO: De-auth user?

    next()
  } else if (isAuthenticated) {
    next()
  } else {
    next({ name: 'sign-in' })
  }
})

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
