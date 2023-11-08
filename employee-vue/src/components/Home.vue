<script>
export default {
  data() {
    return {
      location: null
    }
  },
  methods: {}
}
</script>

<template>
  <div id="app">
    <van-nav-bar
        title="标题"
        left-text="返回"
        left-arrow
        @click-left="getCurrentCity"
    />
    <van-grid>
      <van-grid-item icon="sign" text="考勤签到"/>
      <van-grid-item icon="photo-o" text="请假申请"/>
      <van-grid-item icon="photo-o" text="申请流程"/>
      <van-grid-item icon="photo-o" text="本月出勤记录"/>
    </van-grid>
  </div>
</template>

<style scoped>

</style>