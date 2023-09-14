<template>
  <div id="app">
    <div class="sidebar">
      <card></card>
      <list></list>
    </div>
    <div class="main">
      <message></message>
      <usertext></usertext>
    </div>
  </div>
</template>

<script>
import card from '../card.vue'
import list from '../list.vue'
import message from '../message.vue'
import usertext from '../usertext.vue'

export default {
  name: 'app',
  data() {
    return {}
  },
  created() {
    this.clearMsgCont()
  },
  mounted: function() {
    if (!this.$store.state.hrs) {
      this.$store.dispatch('initData')
    }
  },
  components: {
    card,
    list,
    message,
    usertext
  },
  methods: {
    clearMsgCont() {
      this.$store.state.messageCount = null
      this.$store.state.iSCurrentChat = true
    }
  }
}
</script>

<style lang="scss" scoped>
#app {
  margin: 20px auto;
  width: 800px;
  height: 600px;
  overflow: hidden;
  border-radius: 10px;
  .sidebar,
  .main {
    height: 100%;
  }
  .sidebar {
    float: left;
    color: #f4f4f4;
    background-color: #2e3238;
    width: 200px;
  }
  .main {
    position: relative;
    overflow: hidden;
    background-color: #eee;
  }
}
</style>
