<script setup lang="ts">
import { toTypedSchema } from '@vee-validate/zod';
import * as z from 'zod';
import { useForm } from 'vee-validate';

import { FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { useRoute } from 'vue-router';

const route = useRoute();
const email: string = (route.query.email as string) || '';

const formSchema = toTypedSchema(
  z.object({
    email: z.string().email({ message: 'Ugyldig e-postadresse' }),
  }),
);

const form = useForm({
  initialValues: {
    email: email,
  },
  validationSchema: formSchema,
});

const onSubmit = form.handleSubmit((values) => {
  console.log('Form submitted!', values);
});
</script>

<template>
  <main class="flex flex-row">
    <div class="w-full flex items-center justify-center">
      <div class="flex flex-col justify-center w-sm h-[100svh] gap-16">
        <div class="flex items-center justify-between gap-8">
          <img src="/images/logo.png" alt="Logo" class="w-20" />
          <h1 class="text-4xl">
            <span class="font-semibold">Velkommen til</span>
            <span class="font-extrabold"> KriseFikser</span>
          </h1>
        </div>

        <div class="flex flex-col gap-2 mb-[-0.8rem]">
          <div class="text-3xl font-semibold text-foreground">Tilbakestill passord</div>
          <div class="text-lg text-muted-foreground">
            Vi sender deg en e-post med instruksjoner.
          </div>
        </div>

        <div class="flex flex-col justify-center">
          <Form @submit="onSubmit" class="flex flex-col gap-6">
            <FormField v-slot="{ componentField }" name="email">
              <FormItem>
                <FormLabel>Epost</FormLabel>
                <FormControl>
                  <Input type="text" placeholder="ola.nordmann@mail.no" v-bind="componentField" />
                </FormControl>
                <FormMessage />
              </FormItem>
            </FormField>
            <Button type="submit"> Send meg en e-post </Button>
          </Form>
        </div>
        <div class="flex flex-col justify-center items-center">
          <RouterLink class="hover:underline font-medium ml-3" :to="{ name: 'sign-in' }"
            >Tilbake til innlogging</RouterLink
          >
        </div>
      </div>
    </div>
    <div class="w-full md:block hidden bg-[url(/images/auth-cover.png)] h-[100svh] bg-cover"></div>
  </main>
</template>
