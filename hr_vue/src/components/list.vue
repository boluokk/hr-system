<template>
  <div id="list">
    <ul>
      <li
        v-for="(item, idx) in hrs"
        :key="idx"
        :class="{
          active: currentSession
            ? item.username === currentSession.username
            : false
        }"
        v-on:click="changecurrentSession(item)"
      >
        <!--   :class="[item.id === currentSession ? 'active':'']" -->
        <img class="avatar" :src="item.userface" :alt="item.name" />
        <p class="name">
          {{ item.name }}
        </p>
        <el-badge value="new" class="item" v-if="item.iSNewMsg"> </el-badge>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'list',
  data() {
    return {}
  },
  computed: mapState(['hrs', 'currentSession']),
  methods: {
    changecurrentSession(currentSession) {
      currentSession.iSNewMsg = false
      this.$store.commit('changecurrentSession', currentSession)
    }
  }
}
</script>

<style lang="scss" scoped>
#list {
  img {
    padding-left: 20px;
  }
  p {
    margin-left: 18px;
  }
  ul {
    padding-left: 0;
  }
  li {
    border-bottom: 1px solid #292c33;
    cursor: pointer;
    &:hover {
      background-color: rgba(255, 255, 255, 0.03);
    }
    list-style: none;
  }
  li.active {
    /*注意这个是.不是冒号:*/
    background-color: rgba(255, 255, 255, 0.1);
    margin: 0;
  }
  .avatar {
    border-radius: 2px;
    width: 40px;
    height: 40px;
    vertical-align: middle;
  }
  .name {
    display: inline-block;
    margin-left: 15px;
    font-size: 14px;
  }
  .item {
    margin-left: 10px;
    margin-bottom: 10px;
  }
}
</style>
