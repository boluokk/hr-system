<template>
  <div id='app'>
    <div class='sidebar'>
      <Card></Card>
      <List></List>
    </div>
    <div class='main'>
      <Message></Message>
      <UserText></UserText>
    </div>
  </div>
</template>

<script>
import Card from '@/components/Card.vue'
import List from '@/components/List.vue'
import Message from '@/components/Message.vue'
import UserText from '@/components/UserText.vue'

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
    Card,
    List,
    Message,
    UserText
  },
  methods: {
    clearMsgCont() {
      this.$store.state.messageCount = null
      this.$store.state.iSCurrentChat = true
    }
  }
}
</script>

<style lang='scss' scoped>
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
