<template>
  <div id="app">
    <router-view />
    <global-uploader></global-uploader>
  </div>
</template>

<script>
  import globalUploader from '@/views/background/fileuploader/globalUploader'
export default  {
  name:  'App',
  components: {
    globalUploader
  }
}
</script>
