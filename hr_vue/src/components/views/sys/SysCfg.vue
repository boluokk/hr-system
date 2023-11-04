<template>
  <div>
    <div style='width: 30%'>
      <span>表容量占比</span>
      <el-progress :percentage='50'></el-progress>
      <span>系统内存</span>
      <el-progress :percentage='100' :format='format'></el-progress>
      <span>系统内存</span>
      <el-progress :percentage='100' status='success'></el-progress>
      <span>系统内存</span>
      <el-progress :percentage='100' status='warning'></el-progress>
      <span>系统内存</span>
      <el-progress :percentage='50' status='exception'></el-progress>
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style lang="less" scoped></style>
