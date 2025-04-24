import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  const firstName = ref<string | null>(null)
  const lastName = ref<string | null>(null)
  const email = ref<string | null>(null)

  const setFirstName = (newFirstName: string) => {
    firstName.value = newFirstName
  }

  const setLastName = (newLastName: string) => {
    lastName.value = newLastName
  }

  const setEmail = (newEmail: string) => {
    email.value = newEmail
  }

  return [firstName, lastName, email, setFirstName, setLastName, setEmail]
})
