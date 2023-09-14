<template>
  <div>
    <!--  -->
    <div style="margin-top: 15px;" class="block">
      <div style="width: 45%;">
        <el-input placeholder="输入关键字进行过滤" v-model="filterText">
        </el-input>
        <el-tree
          :data="manageData"
          :props="ManadefaultProps"
          default-expand-all
          :filter-node-method="filterNode"
          ref="tree"
          :expand-on-click-node="false"
          style="margin-top: 15px;"
        >
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}</span>
            <span>
              <el-button type="primary" size="mini" @click="add(data)">
                添加
              </el-button>
              <el-button type="info" size="mini" @click="edit(data)">
                编辑
              </el-button>
              <el-button type="danger" size="mini" @click="remove(node, data)">
                删除
              </el-button>
            </span>
          </span>
        </el-tree>
      </div>

      <div style="width: 45%;margin-left: 10%;">
        <el-input
          placeholder="根据部门名查询"
          style="width: 40%;margin-right: 15px;"
          v-model="searchData"
        ></el-input>
        <el-button type="primary" @click="searchBtn">查询</el-button>
        <el-table :data="manageData2" stripe style="margin-top: 15px;">
          <el-table-column prop="id" label="部门编号" width="180">
          </el-table-column>
          <el-table-column prop="name" label="部门名称" width="180">
          </el-table-column>
          <el-table-column label="状态(关闭/开启)">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.enabled"
                active-color="#13ce66"
                inactive-color="#ff4949"
                @change="changeButton(scope.row)"
              >
              </el-switch>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>

    <el-dialog title="提示" :visible.sync="ManadialogVisible" width="30%">
      <el-form
        label-position="left"
        label-width="100px"
        :model="formLabelAlign"
      >
        <el-form-item label="名称">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <el-form-item label="开启/关闭">
          <el-switch
            v-model="formLabelAlign.enabled"
            active-text="开启"
            inactive-text="关闭"
            active-color="#13ce66"
            inactive-color="#ff4949"
          >
          </el-switch>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="ManadialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="ManadialogVisibleSubmit"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <!-- 添加 -->
    <el-dialog title="提示" :visible.sync="Mana2dialogVisible" width="30%">
      <el-form
        label-position="left"
        label-width="100px"
        :model="formLabelAlign2"
      >
        <el-form-item label="名称">
          <el-input v-model="formLabelAlign2.childrenName"></el-input>
        </el-form-item>
        <el-form-item label="开启/关闭">
          <el-switch
            v-model="formLabelAlign2.childrenEnabled"
            active-text="开启"
            inactive-text="关闭"
            active-color="#13ce66"
            inactive-color="#ff4949"
          >
          </el-switch>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="Mana2dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="Mana2dialogVisibleSubmit"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: {
    manageData: Array,
    initMana: Function
  },
  data() {
    return {
      ManadefaultProps: {
        label: 'name',
        children: 'children'
      },
      filterText: '',
      ManadialogVisible: false,
      formLabelAlign: {
        id: '',
        name: '',
        enabled: true
      },
      formLabelAlign2: {
        parentId: '',
        childrenName: '',
        childrenEnabled: true,
        parentIsParent: false,
        parentDepPath: ''
      },
      Mana2dialogVisible: false,
      searchData: '',
      manageData2: []
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  created() {
    this.initEnabF()
  },
  methods: {
    initEnabF() {
      this.getRequest('/system/basic/department/enab/f').then(res => {
        this.manageData2 = res.data.obj
      })
    },
    searchBtn() {
      this.getRequest(
        '/system/basic/department/search/' + this.searchData
      ).then(res => {
        this.manageData2 = res.data.obj
        this.searchData = ''
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    edit(data) {
      this.ManadialogVisible = true
      this.formLabelAlign.name = data.name
      this.formLabelAlign.id = data.id
    },
    ManadialogVisibleSubmit() {
      if (this.formLabelAlign.name.trim() !== '' && this.formLabelAlign.name) {
        this.putRequest('/system/basic/department/change/one', {
          id: this.formLabelAlign.id,
          name: this.formLabelAlign.name,
          enabled: this.formLabelAlign.enabled
        }).then(res => {
          this.$message.success(res.data.msg)
          this.ManadialogVisible = false
          this.formLabelAlign.enabled = true
          this.initMana()
          this.initEnabF()
        })
      } else {
        this.$message.error('请填写必要项！')
      }
    },
    remove(node, data) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteRequest('/system/basic/department/delete', {
            parentId: data.parentid,
            parentDepPath: data.deppath
          }).then(res => {
            if (res.data) {
              this.$message.success(res.data.msg)
              this.initMana()
            }
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    changeButton(item) {
      this.putRequest('/system/basic/department/change/one', {
        id: item.id,
        enabled: item.enabled
      }).then(res => {
        this.$message.success(res.data.msg)
        this.initMana()
        this.initEnabF()
      })
    },
    add(data) {
      this.Mana2dialogVisible = true
      this.formLabelAlign2.parentId = data.id
      this.formLabelAlign2.parentIsParent = data.isparent
      this.formLabelAlign2.parentDepPath = data.deppath
    },
    Mana2dialogVisibleSubmit() {
      if (
        this.formLabelAlign2.childrenName.trim() !== '' &&
        this.formLabelAlign2.childrenName
      ) {
        this.putRequest('/system/basic/department/', this.formLabelAlign2).then(
          res => {
            this.$message.success(res.data.msg)
            this.formLabelAlign2.childrenName = ''
            this.Mana2dialogVisible = false
            this.formLabelAlign2.childrenEnabled = true
            this.initMana()
          }
        )
      }
    }
  }
}
</script>

<style lang="less" scoped>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 12px;
}
.el-button--mini {
  padding: 5px;
}
.block {
  flex: 1;
  display: flex;
  justify-content: space-between;
}
</style>
