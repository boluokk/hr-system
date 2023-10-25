<template>
  <div>
    <el-breadcrumb separator='/' style='margin-bottom: 15px;'>
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>操作员管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class='box-card lightColor' :style='fatherCard'>
      <div class='searchInput'>
        <el-input
          v-on:keydown.enter.native='searchKeyDown'
          placeholder='请输入人事名称...'
          v-model='hrName'
          style='width: 500px; margin: 0 50%;transform: translateX(-50%);display:inline-block;'
          clearable>
        </el-input>
      </div>
      <div style='margin-bottom:20px;'>
        <!--        <span class='lightbox' :style='changeCloStyle'>-->
        <!--          <span class='light'>-->
        <!--          <i class='el-icon-eleme light2' @click='changeTop'></i>-->
        <!--        </span>-->
        <!--        </span>-->
        <el-tag
          class='tagCls'
          @click='ShowAddHr'
          type='success'
          effect='plain'>
          添加角色
        </el-tag>
      </div>
      <div class='hrbox'>
        <el-card class='box-card' shadow='hover'
                 style='position: relative;width: 300px;height: 400px;display:inline-block;margin-left:40px;margin-bottom:50px;'
                 v-for='(item,idx) in initData' :key='idx'>
          <div
            slot='header'
            class='clearfix'
            style='text-align:center;margin-left:15px;'
          >
            <span>{{ item.name }}</span>
            <el-popconfirm
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon='el-icon-info'
              icon-color='red'
              title='确定删除吗？'
              @confirm='deleteHr(item.id)'
            >
              <el-button
                slot='reference'
                style='float: right; padding: 3px 0;color: red;'
                type='text'
                icon='el-icon-delete'
              ></el-button>
            </el-popconfirm>

          </div>
          <div style='text-align:center;'>
            <el-avatar :size='60' :src='item.userFace' style=''></el-avatar>
          </div>
          <div style='font-size: 12px;margin-top:15px;'>
            用户名：<span>{{ item.name }}</span><br>
            手机号码：<span>{{ item.phone }}</span><br>
            电话号码：<span>{{ item.telephone }}</span><br>
            地址：<span>{{ item.address }}</span><br>
            用户状态：
            <el-switch
              @change='changeSelect(item)'
              active-color='#13ce66'
              inactive-color='#ff4949'
              style='margin-bottom: 1px;'
              v-model='item.enabled'
              active-text='开启'
              inactive-text='禁用'>
            </el-switch>
            <br>
            用户角色：
            <el-tag type='warning' size='mini' style='margin-right: 5px;margin-top: 1px;' effect='plain'
                    v-for='(item,idx) in item.roles' :key='idx' v-text='item.nameZh'></el-tag>
            <el-popover
              placement='bottom'
              @hide='addSelectRoles(item.id)'
              width='100'
              trigger='click'>
              <el-select
                v-model='selectRoles'
                multiple
                filterable
                allow-create
                default-first-option
                placeholder='请选择'>
                <el-option
                  v-for='item in options'
                  :key='item.id'
                  :label='item.nameZh'
                  :value='item.id'>
                </el-option>
              </el-select>
              <el-link type='primary' slot='reference' :underline='false' style='margin-left:7px;font-size: 13px;'
                       icon='el-icon-edit-outline' @click='editShow(item.id)'>修改
              </el-link>
            </el-popover>
            <br>
          </div>
          <div class='remark'>
            <b>备注：</b>
            <div style='margin: 20px 0;'></div>
            <el-input
              type='textarea'
              placeholder='请输入内容'
              v-model='item.remark'
              maxlength='30'
              show-word-limit
              @blur='remarkAdd(item)'
            >
            </el-input>
            <br>
          </div>
        </el-card>
      </div>
    </el-card>
    <el-drawer
      :before-close='handleClose'
      :visible.sync='queryTableVisibleDialog'
      direction='ltr'
      custom-class='demo-drawer'
      ref='drawer'
    >
      <div>
        <el-form :model='hrForm'
                 style='padding: 0 20px;'
                 :rules='hrRules'
                 ref='ruleForm'>
          <el-form-item label='名称' prop='name'>
            <el-input v-model='hrForm.name'></el-input>
          </el-form-item>
          <el-form-item label='手机' prop='phone'>
            <el-input v-model='hrForm.phone' autocomplete='off' maxlength='11' show-word-limit></el-input>
          </el-form-item>
          <el-form-item label='电话' prop='telephone'>
            <el-input v-model='hrForm.telephone' autocomplete='off'></el-input>
          </el-form-item>
          <el-form-item label='地址' prop='address'>
            <el-input v-model='hrForm.address' autocomplete='off'></el-input>
          </el-form-item>
          <el-form-item label='账号' prop='username'>
            <el-input v-model='hrForm.username' autocomplete='off'></el-input>
          </el-form-item>
          <el-form-item label='密码' prop='password'>
            <el-input v-model='hrForm.password' autocomplete='off'></el-input>
          </el-form-item>
          <el-form-item label='头像网址' prop='userFace'>
            <el-input v-model='hrForm.userFace' autocomplete='off'></el-input>
          </el-form-item>
        </el-form>
        <div class='demo-drawer__footer' style='width: 200px;margin: 0 50%;transform: translateX(-50%);'>
          <el-button @click='cancelForm' size='small'>取 消</el-button>
          <el-button type='primary' size='small' :loading='loading'>
            {{ loading ? '提交中 ...' : '确 定' }}
          </el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    let validatePass = (rule, value, callback) => {
      // 以字母开头，长度在6~18之间，只能包含字母、数字和下划线
      let reg = new RegExp(/^[a-zA-Z]\w{5,17}$/)
      if (!reg.test(value)) {
        callback(new Error('以字母开头，长度在6~18之间，只能包含字母、数字和下划线!'))
      } else {
        callback()
      }
    }
    return {
      initData: [],
      // select 选择器数据
      options: [],
      selectRoles: [],
      // 拉链style
      changeCloStyle: {},
      queryTableVisibleDialog: false,
      loading: false,
      hrName: '',
      fatherCard: {
        isRight: true,
        position: 'relative',
        background: ''
      },
      hrRules: {
        name: [
          { required: true, message: '请输入内容', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入内容', trigger: 'blur' },
          { min: 11, max: 11, message: '长度不为11个', trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '请输入内容', trigger: 'blur' },
          { min: 11, max: 11, message: '长度不为11个', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入内容', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入内容', trigger: 'blur' }
        ],
        password: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ],
        userFace: [
          { required: true, message: '请输入内容', trigger: 'blur' },
          { min: 1, max: 255, message: '长度最长为255', trigger: 'blur' }
        ]
      },
      hrForm: {
        name: '',
        phone: '',
        telephone: '',
        address: '',
        username: '',
        password: '',
        userFace: '',
        enabled: true
      }
    }
  },
  methods: {
    searchKeyDown() {
      this.postRequest('/sys/hr/byHrName/' + this.hrName).then(res => {
        this.initData = res.data
      })
    },
    addHr() {
      this.$refs.ruleForm.validate(vali => {
        if (vali) {
          this.putRequest('/sys/hr/add', this.hrForm).then(res => {
            this.$message.success(res.data.msg)
            this.$refs.ruleForm.resetFields()
            this.init()
          })
        } else {
          this.$message.error('请填写必须项!')
        }
      })
    },
    ShowAddHr() {
      this.queryTableVisibleDialog = true
    },
    remarkAdd(role) {
      if (role.remark) {
        if (role.remark.trim() !== '') {
          this.putRequest('/sys/hr/modify', {
            id: role.id,
            enabled: role.enabled,
            remark: role.remark
          }).then(resp => {
            this.$message.success(resp.data.msg)
            this.init()
          })
        }
      }
    },
    deleteHr(id) {
      this.deleteRequest('/sys/hr/delete/' + id).then(res => {
        this.$message.success(res.data.msg)
        this.init()
      })
    },
    changeSelect(role) {
      this.putRequest('/sys/hr/modify', {
        enabled: role.enabled,
        id: role.id
      }).then(resp => {
        this.$message.success(resp.data.msg)
      })
    },
    init() {
      this.getRequest('/sys/hr/').then(resp => {
        this.initData = resp.data
      })
    },
    editShow(id) {
      this.selectRoles = []
      this.getRequest('/sys/hr/all/roles').then(resp => {
        this.options = resp.data
      })
      this.getRequest('/sys/hr/byHrId/' + id).then(resp => {
        resp.data.forEach(item => {
          this.selectRoles.push(item.id)
        })
      })
    },
    addSelectRoles(id) {
      this.putRequest('/sys/hr/many/roles', {
        hrId: id,
        roleIds: this.selectRoles
      }).then(resp => {
        this.$message.success(resp.data.msg)
        this.init()
      })
    },
    handleClose(done) {
      if (this.loading) {
        return
      }
      this.$confirm('确定要提交表单吗？')
        .then(_ => {
          this.loading = true
          this.timer = setTimeout(() => {
            done()
            // 动画关闭需要一定的时间
            setTimeout(() => {
              this.loading = false
              this.addHr()
            }, 400)
          }, 2000)
        })
        .catch(_ => {
          this.queryTableVisibleDialog = false
        })
    },
    cancelForm() {
      this.loading = false
      this.queryTableVisibleDialog = false
      clearTimeout(this.timer)
    }
  },
  created() {
    this.init()
  },
  watch: {
    input(val) {
      if (val.trim() === '') {
        this.init()
      }
    }
  }
}
</script>

<style lang='less' scoped>
.remark {
  position: absolute;
  bottom: 20px;
  left: 20px;
  font-size: 12px;
  width: 80%;
}

.tagCls {
  transform: rotateZ(19deg);
  position: absolute;
  right: 0;
  top: 2px;
  cursor: pointer;
}

.tagCls:hover {
  color: rgb(204, 0, 255);
  border: rgb(233, 80, 220) 1px solid;
}

.lightbox {
  position: absolute;
  right: 130px;
  top: -11px;
  transition: top 1s;
}

.light {
  display: inline-block;
  background-color: blueviolet;
  height: 40px;
  width: 2px;
}

.light2 {
  display: inline-block;
  position: absolute;
  top: 36px;
  right: -7px;
  cursor: pointer;
}

.hrbox {
  margin-top: 40px;
}
</style>
