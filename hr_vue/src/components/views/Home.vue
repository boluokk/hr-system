<template>
  <div>
    <el-container>
      <!-- 头部 -->
      <el-header>
        <span>
          <svg class='icon' aria-hidden='true'>
            <use xlink:href='#icon-boluo3'></use>
          </svg>
          <span style='font-size: 30px; font-family: 华文新魏,serif;'
          ><b>@人事管理</b></span
          >
        </span>

        <el-dropdown style='margin-right: 15px;' @command='commandHandel'>
          <span class='el-dropdown-link'>
            <el-avatar class='avatar' :src='currentHrimg'></el-avatar>
          </span>
          <el-dropdown-menu slot='dropdown'>
            <el-dropdown-item icon='el-icon-user' command='my'
            >我的
            </el-dropdown-item
            >
            <el-dropdown-item icon='el-icon-s-tools' command='setting'
            >设置
            </el-dropdown-item
            >
            <el-dropdown-item icon='el-icon-chat-dot-square' command='msg'>
              聊天室
              <el-badge :value='msgCount' class='item' type='danger'></el-badge
              >
            </el-dropdown-item>
            <el-dropdown-item icon='el-icon-switch-button' command='logout'
            >退出
            </el-dropdown-item
            >
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <!-- 菜单栏 -->
      <el-container>
        <el-aside width='200px' style='background-color: #70ebda'>
          <el-menu background-color='#70ebda' unique-opened router>
            <el-submenu
              :index="index + ''"
              v-for='(item, index) in routes'
              :key='index'
            >
              <template slot='title'
              ><i class='el-icon-message'></i>{{ item.name }}
              </template
              >
              <el-menu-item-group
                v-for='(item2, index2) in item.children'
                :key='index2'
              >
                <el-menu-item :index='item2.path'>{{
                    item2.name
                  }}
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <div class='welcome' v-if="this.$router.currentRoute.path === '/home'">
            <!-- 欢迎来到人事管理！ -->
          </div>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { getRequest } from '@/assets/js/AjaxUtil'

export default {
  data() {
    return {
      currentHrimg: this.$store.state.user.userface,
      getRequest: getRequest
    }
  },
  methods: {
    commandHandel(command) {
      if (command === 'my') {
      } else if (command === 'settting') {
      } else if (command === 'logout') {
        this.logout()
      } else if (command === 'msg') {
        this.$router.push('/chat')
      }
    },
    logout() {
      this.$confirm('此操作将退出注销, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.getRequest('/logout').then(res => {
            if (res.data.status === 200) {
              this.$message.success('注销成功!')
              window.sessionStorage.removeItem('user')
              this.$store.state.routes = []
              this.$router.replace('/')
            }
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
    }
  },
  computed: {
    routes() {
      return this.$store.state.routes
    },
    msgCount() {
      return this.$store.state.messageCount
    }
  },
  watch: {}
}
</script>

<style lang='less' scoped>
.el-header {
  background-color: #70ebda;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.icon {
  width: 35px;
  height: 30px;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

.welcome {
  //font-family: 华文新魏,serif;
  font-size: 50px;
  margin: 50px 500px;
}

.avatar {
  cursor: pointer;
}
</style>
