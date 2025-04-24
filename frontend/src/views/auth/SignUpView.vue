<script setup lang="ts">
import { toTypedSchema } from '@vee-validate/zod'
import * as z from 'zod'
import { useForm } from 'vee-validate'

import { FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import { RouterLink } from 'vue-router'

const formSchema = toTypedSchema(
  z.object({
    firstName: z.string().min(2, 'Må være minst 2 bokstaver'),
    lastName: z.string().min(2, 'Må være minst 2 bokstaver'),
    email: z.string().email({ message: 'Ugyldig e-postadresse' }),
    password: z
      .string()
      .min(8, { message: 'Passordet må være minst 8 tegn' })
      .regex(/\d.*/, { message: 'Passordet må inneholde minst ett tall' })
      .regex(/[!"#$%&/()=?*^¨\.;:\_\-].*/, {
        message: 'Passordet må inneholde minst ett spesialtegn',
      })
      .regex(/[a-zæøå].*/, { message: 'Passordet må inneholde minst én liten bokstav' })
      .regex(/[A-ZÆØÅ].*/, { message: 'Passordet må inneholde minst én stor bokstav' }),
    confirmPassword: z.string().refine(
      (): boolean => {
        return form.values.password === form.values.confirmPassword
      },
      { message: 'Passordene må være like' },
    ),
  }),
)

const form = useForm({
  validationSchema: formSchema,
})

const onSubmit = form.handleSubmit((values) => {
  console.log('Form submitted!', values)
})
</script>

<template>
  <main class="flex flex-row">
    <div class="w-full flex items-center justify-center">
      <div class="flex flex-col justify-center w-sm h-[100svh] gap-16">
        <div class="flex flex-col items-center justify-center">
          <img src="/images/logo.png" alt="Logo" class="w-20" />
          <h1 class="text-4xl">Velkommen tilbake til <span class="font-bold">KriseFikser</span></h1>
        </div>

        <div class="flex flex-col justify-center">
          <Form @submit="onSubmit" class="flex flex-col gap-8">
            <div class="flex flex-row gap-3">
              <FormField v-slot="{ componentField }" name="firstName">
                <FormItem>
                  <FormLabel>Fornavn</FormLabel>
                  <FormControl>
                    <Input type="text" placeholder="Ola" v-bind="componentField" />
                  </FormControl>
                  <FormMessage class="max-h-0" />
                </FormItem>
              </FormField>
              <FormField v-slot="{ componentField }" name="lastName">
                <FormItem>
                  <FormLabel>Etternavn</FormLabel>
                  <FormControl>
                    <Input type="text" placeholder="Nordmann" v-bind="componentField" />
                  </FormControl>
                  <FormMessage class="max-h-0" />
                </FormItem>
              </FormField>
            </div>
            <FormField v-slot="{ componentField }" name="email">
              <FormItem>
                <FormLabel>Epost</FormLabel>
                <FormControl>
                  <Input type="text" placeholder="ola.nordmann@mail.no" v-bind="componentField" />
                </FormControl>
                <FormMessage class="max-h-0" />
              </FormItem>
            </FormField>
            <FormField v-slot="{ componentField }" name="password" class="w-full">
              <FormItem class="w-full">
                <FormLabel>Passord</FormLabel>
                <FormControl>
                  <Input type="password" v-bind="componentField" />
                </FormControl>
                <FormMessage class="max-h-0" />
              </FormItem>
            </FormField>
            <FormField v-slot="{ componentField }" name="confirmPassword" class="w-full">
              <FormItem class="w-full">
                <FormLabel>Bekreft passord</FormLabel>
                <FormControl>
                  <Input type="password" v-bind="componentField" />
                </FormControl>
                <FormMessage class="max-h-0" />
              </FormItem>
            </FormField>
            <Button type="submit"> Submit </Button>
          </Form>
        </div>
        <div class="flex flex-col justify-center items-center">
          <div>
            Har du allerede en konto?
            <RouterLink class="hover:underline font-medium ml-3" :to="{ name: 'sign-in' }"
              >Logg inn</RouterLink
            >
          </div>
        </div>
      </div>
    </div>
    <div class="w-full md:block hidden bg-[url(/images/auth-cover.png)] h-[100svh] bg-cover"></div>
  </main>
</template>
