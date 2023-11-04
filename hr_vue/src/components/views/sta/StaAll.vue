<template>
  <div id='app'>
    <el-breadcrumb separator='/' style='margin-bottom:15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>综合信息统计</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter='20'>
        <el-col :span='6'>
          <div>
            <el-statistic
              group-separator=','
              :value='headerData.employeeCount'
              title='员工人数'
            ></el-statistic>
          </div>
        </el-col>
        <el-col :span='6'>
          <div>
            <el-statistic title='部门数'>
              <template slot='formatter'>{{ headerData.departmentCount }}</template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span='6'>
          <div>
            <el-statistic
              group-separator=','
              decimal-separator='.'
              :value='headerData.jobLevelCount'
              title='职位数'
            >
            </el-statistic>
          </div>
        </el-col>
        <el-col :span='6'>
          <div>
            <el-statistic :value='headerData.roleCount' title='角色数'></el-statistic>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <el-card class='card_cls'>
      <div id='salary' class='ghCls'></div>
    </el-card>
    <el-card class='card_cls'>
      <div id='department' class='ghCls' style='height: 650px;'></div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      headerData: {
        allCount: 0,
        punishmentCount: 0,
        rewardCount: 0
      },
      salaryData: {
        toolbox: {
          show: true,
          feature: {
            magicType: { type: ['line', 'bar'] },
            saveAsImage: {}
          }
        },
        title: {
          text: '工资账套'
        },
        dataset: {
          source: [
            ['score', 'amount', 'product'],
            [Math.random() * 100, 1, 'w'],
            [Math.random() * 100, 2, 'e']
          ]
        },
        grid: { containLabel: true },
        xAxis: { name: 'amount' },
        yAxis: { type: 'category' },
        visualMap: {
          orient: 'horizontal',
          left: 'left',
          min: 10,
          max: 100,
          dimension: 0,
          inRange: {
            color: ['#65B581', '#FFCE34', '#FD665F']
          }
        },
        series: [
          {
            type: 'bar',
            encode: {
              x: 'amount',
              y: 'product'
            }
          }
        ]
      },
      departmentData: {
        toolbox: {
          show: true,
          feature: {
            saveAsImage: {}
          }
        },
        title: {
          text: '部门结构'
        },
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: [
          {
            type: 'tree',
            id: 0,
            name: 'tree1',
            data: [],
            top: '5%',
            left: '8%',
            bottom: '5%',
            right: '20%',
            symbolSize: 10,
            edgeShape: 'polyline',
            edgeForkPosition: '63%',
            initialTreeDepth: 5,
            lineStyle: {
              width: 2
            },
            label: {
              backgroundColor: '#fff',
              position: 'left',
              verticalAlign: 'middle',
              align: 'right'
            },
            leaves: {
              label: {
                position: 'right',
                verticalAlign: 'middle',
                align: 'left'
              }
            },
            emphasis: {
              focus: 'descendant'
            },
            expandAndCollapse: true,
            animationDuration: 550,
            animationDurationUpdate: 750
          }
        ]
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    ghCreate() {
      this.$echarts.init(document.getElementById('salary')).setOption(this.salaryData)
      this.$echarts.init(document.getElementById('department')).setOption(this.departmentData)
    },
    init() {
      this.getRequest('/sta/all/header/count').then(res => {
        this.headerData = res.data.obj
      })
      let defaultArr = [
        ['score', 'amount', 'product']
      ]
      this.getRequest('/sta/all/salary').then(res => {
        let data = res.data.obj
        data.forEach(item => {
          defaultArr.push([
            Math.random() * 100,
            item.salary,
            item.name
          ])
        })
        this.salaryData.dataset.source = defaultArr
        this.ghCreate()
      })
      this.getRequest('/sta/all/department/struct').then(res => {
        this.departmentData.series[0].data = [res.data.obj]
        this.ghCreate()
        console.log(this.departmentData)
      })
    }
  }
}
</script>

<style scoped>
.ghCls {
  width: auto;
  height: 500px;
}

.card_cls {
  margin-top: 20px;
}
</style>

