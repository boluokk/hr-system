<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>基本资料</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card shadow="always">
      <div class="top">
        <span>
          <el-input
            v-model="input"
            placeholder="请输入内容"
            size="mini"
            style="width: 350px;"
            prefix-icon="el-icon-search"
          ></el-input>
          <el-button
            type="primary"
            size="small"
            @click="serch"
            :disabled="serchDisabled"
            icon="el-icon-search"
            >搜索</el-button
          >
          <el-button
            type="primary"
            size="small "
            @click="tipSerch"
            icon="el-icon-cpu"
            >高级搜索</el-button
          >
        </span>
        <span class="rightBtn">
          <el-upload
            action="/personnel/emp/import"
            :before-upload="beforeUpload"
            :show-file-list="false"
            :on-success="uploadSuccess"
            :on-error="uploadError"
            :disabled="uploadIsDis"
          >
            <el-button
              size="small"
              type="success"
              @click="imporT"
              :icon="uploadIcon"
              >点击上传</el-button
            >
          </el-upload>

          <!-- <el-button
            type="success"
            size="small "
            @click="imporT"
            icon="el-icon-upload2"
            >上传数据</el-button
          > -->

          <el-button
            type="success"
            size="small "
            @click="exporT"
            icon="el-icon-download"
            >下载数据</el-button
          >

          <el-button
            type="info"
            size="small "
            @click="showAddDia"
            icon="el-icon-circle-plus"
            >添加用户</el-button
          >
        </span>
      </div>
      <transition name="slide-fade">
        <div
          v-show="isVisabledToTipSearch"
          style="border: 2px solid #70ebda;border-radius:5px;margin-top: 10px;"
        >
          <el-row :gutter="20">
            <el-col
              :span="4"
              :offset="0"
              style="margin-top: 5px;margin-left: 10px;"
            >
              <span>政治面貌：</span>
              <el-select
                v-model="serchData.politicid"
                placeholder="请选择"
                style="width: 100px"
              >
                <el-option
                  v-for="item in politdataP"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="4" :offset="0" style="margin-top: 5px;">
              <span>民族：</span>
              <el-select
                v-model="serchData.nationid"
                style="width: 100px"
                placeholder="请选择"
              >
                <el-option
                  v-for="item in nationDatas"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="4" :offset="0" style="margin-top: 5px;">
              <span>职位：</span>
              <el-select
                v-model="serchData.posid"
                placeholder="请选择"
                style="width: 100px"
              >
                <el-option
                  v-for="item in jobLeveData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="4" :offset="0" style="margin-top: 5px;">
              <span>职称：</span>
              <el-select
                v-model="serchData.joblevelid"
                style="width: 100px"
                placeholder="请选择"
              >
                <el-option
                  v-for="item in jobNameData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="7" :offset="0" style="margin-top: 12px;">
              <span>雇佣方式：</span>
              <el-radio v-model="serchData.engageform" label="劳动合同"
                >劳动合同</el-radio
              >
              <el-radio v-model="serchData.engageform" label="劳务合同"
                >劳务合同</el-radio
              >
            </el-col>
          </el-row>
          <el-row :gutter="20" style="margin-bottom: 10px;">
            <el-col :span="6" :offset="0" style="margin: 10px 0 0 10px">
              <span>所属部门：</span>
              <el-select v-model="serchData.departmentid" placeholder="请选择">
                <el-option
                  v-for="item in departmentData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="12" :offset="0" style="margin: 10px 0 0 10px">
              <span>入职日期：</span>
              <el-date-picker
                v-model="value2"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-col>
            <el-col :span="4" :offset="0" style="margin: 10px 0 0 10px">
              <el-button size="mini" @click="cancelTipSearch">取消</el-button>
              <el-button
                type="primary"
                icon="el-icon-search"
                size="mini"
                @click="tipSerchBtn"
                >搜索</el-button
              >
            </el-col>
          </el-row>
        </div>
      </transition>
      <el-table
        :data="tableData"
        border
        style="width: 100%;margin-top: 15px;"
        v-loading="loading"
      >
        <el-table-column type="selection" width="55" fixed></el-table-column>
        <el-table-column prop="name" label="姓名" width="90" fixed>
        </el-table-column>
        <el-table-column prop="workid" label="工号" width="100" fixed>
        </el-table-column>
        <el-table-column label="出生日期" width="170">
          <template slot-scope="scope">
            {{ scope.row.birthday | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop="idcard" label="身份证号码" width="180">
        </el-table-column>
        <el-table-column prop="wedlock" label="婚姻状态" width="60">
        </el-table-column>
        <el-table-column label="名族" width="90">
          <template slot-scope="scope">
            {{ scope.row.nation.name }}
          </template>
        </el-table-column>
        <el-table-column prop="nativeplace" label="籍贯" width="100">
        </el-table-column>
        <el-table-column label="政治面貌" width="110">
          <template slot-scope="scope">
            {{ scope.row.politicsstatus.name }}
          </template>
        </el-table-column>
        <el-table-column prop="email" label="电子邮件" width="170">
        </el-table-column>
        <el-table-column prop="phone" label="手机号码" width="120">
        </el-table-column>
        <el-table-column label="所属部门" width="100">
          <template slot-scope="scope">
            {{ scope.row.department.name }}
          </template>
        </el-table-column>
        <el-table-column prop="address" label="联系地址" width="250">
        </el-table-column>
        <el-table-column label="职位" width="120">
          <template slot-scope="scope">
            {{ scope.row.position.name }}
          </template>
        </el-table-column>
        <el-table-column label="职称" width="120">
          <template slot-scope="scope">
            {{ scope.row.joblevel.name }}
          </template>
        </el-table-column>
        <el-table-column prop="tiptopdegree" label="学历" width="60">
        </el-table-column>
        <el-table-column prop="school" label="毕业院校" width="180">
        </el-table-column>
        <el-table-column prop="specialty" label="大学专业" width="120">
        </el-table-column>
        <el-table-column label="入职日期" width="170">
          <template slot-scope="scope">
            {{ scope.row.begindate | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label="转正日期" width="170">
          <template slot-scope="scope">
            {{ scope.row.conversiontime | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label="合同起始日期" width="170">
          <template slot-scope="scope">
            {{ scope.row.begincontract | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column label="合同终止日期" width="170">
          <template slot-scope="scope">
            {{ scope.row.endcontract | dateFormat }}
          </template>
        </el-table-column>
        <el-table-column prop="engageform" label="聘用方式" width="100">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="200">
          <template slot-scope="scope">
            <el-button
              @click="handleClick(scope.row)"
              type="info"
              size="mini"
              icon="el-icon-setting"
              >编辑</el-button
            >
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              @click="remove(scope.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="1"
        :page-sizes="[1, 3, 5, 10]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        class='el_page_div'
      >
      </el-pagination>
    </el-card>
    <el-dialog :title="titleIsEOrA" :visible.sync="dialogVisible" width="95%">
      <!-- 表单 -->
      <el-form
        ref="empformRef"
        :model="empform"
        label-width="120px"
        label-position="right"
        :rules="empFormRoles"
      >
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item prop="name" label="姓名:">
              <el-input v-model="empform.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="性别:">
              <el-radio v-model="empform.gender" label="男">男</el-radio>
              <el-radio v-model="empform.gender" label="女">女</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="birthday" label="出生日期:" label-width="120px">
              <el-date-picker
                v-model="empform.birthday"
                type="date"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="politicid" label="政治面貌:">
              <el-select v-model="empform.politicid" placeholder="请选择">
                <el-option
                  v-for="item in politdataP"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item prop="nationid" label="民族:">
              <el-select v-model="empform.nationid" placeholder="请选择">
                <el-option
                  v-for="item in nationDatas"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="nativeplace" label="籍贯:">
              <el-input v-model="empform.nativeplace"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="email" label="邮箱:">
              <el-input placeholder="请输入内容" v-model="empform.email">
                <template slot="append">qq.com</template>
              </el-input></el-form-item
            >
          </el-col>
          <el-col :span="6">
            <el-form-item prop="phone" label="电话:">
              <el-input placeholder="请输入内容" v-model="empform.phone">
              </el-input
            ></el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item prop="address" label="住址:">
              <el-input placeholder="请输入内容" v-model="empform.address">
              </el-input
            ></el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="departmentid" label="所属部门:">
              <el-select v-model="empform.departmentid" placeholder="请选择">
                <el-option
                  v-for="item in departmentData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="joblevelid" label="职称:">
              <el-select v-model="empform.joblevelid" placeholder="请选择">
                <el-option
                  v-for="item in jobNameData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="posid" label="职位:">
              <el-select v-model="empform.posid" placeholder="请选择">
                <el-option
                  v-for="item in jobLeveData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="雇佣方式:">
              <el-radio v-model="empform.engageform" label="劳动合同"
                >劳动合同</el-radio
              >
              <el-radio v-model="empform.engageform" label="劳务合同"
                >劳务合同</el-radio
              >
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="tiptopdegree" label="学历:">
              <el-select v-model="empform.tiptopdegree" placeholder="请选择">
                <el-option
                  v-for="(item, idx) in tiptopdes"
                  :key="idx"
                  :label="item.name"
                  :value="item.name"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="specialty" label="专业:">
              <el-input v-model="empform.specialty"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="school" label="学校:">
              <el-input v-model="empform.school"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6" :offset="0">
            <el-form-item prop="begindate" label="入职日期:">
              <el-date-picker
                v-model="empform.begindate"
                align="right"
                type="date"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="0">
            <el-form-item prop="conversiontime" label="转正日期:">
              <el-date-picker
                v-model="empform.conversiontime"
                align="right"
                type="date"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="0">
            <el-form-item prop="begincontract" label="合同起始日期:">
              <el-date-picker
                v-model="empform.begincontract"
                align="right"
                type="date"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="0">
            <el-form-item prop="endcontract" label="合同终止日期:">
              <el-date-picker
                v-model="empform.endcontract"
                align="right"
                type="date"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="6" :offset="0">
            <el-form-item prop="idcard" label="身份证:">
              <el-input v-model="empform.idcard"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="0">
            <el-form-item prop="wedlock" label="婚姻:">
              <el-radio v-model="empform.wedlock" label="已婚">已婚</el-radio>
              <el-radio v-model="empform.wedlock" label="未婚">未婚</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="6" :offset="0">
            <el-form-item label="工号:">
              <el-input v-model="empform.workid" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="Adduser">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { politdata } from '../../../assets/js/DataHelp/politicsstatus.js'
import { nationData } from '../../../assets/js/DataHelp/nation.js'
import { tiptopde } from '../../../assets/js/DataHelp/tiptopdegree.js'

export default {
  data() {
    return {
      input: '',
      tableData: [],
      total: 0,
      pageSize: 10,
      pageNum: 1,
      dialogVisible: false,
      empform: {
        id: '',
        name: '',
        gender: '男',
        birthday: '',
        politicid: '',
        nationid: '',
        nativeplace: '',
        email: '',
        phone: '',
        address: '',
        departmentid: '',
        joblevelid: '',
        posid: '',
        engageform: '劳动合同',
        tiptopdegree: '',
        specialty: '',
        school: '',
        begindate: '',
        conversiontime: '',
        begincontract: '',
        endcontract: '',
        idcard: '',
        wedlock: '已婚',
        workid: ''
      },
      politdataP: politdata,
      nationDatas: nationData,
      tiptopdes: tiptopde,
      departmentData: [],
      jobNameData: [],
      jobLeveData: [],
      empFormRoles: {
        name: [{ required: true, message: '请输入相关参数', trigger: 'blur' }],
        gender: '男',
        birthday: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        politicid: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        nationid: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        nativeplace: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入相关参数', trigger: 'blur' },
          {
            type: 'email',
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        phone: [
          { required: true, message: '请输入相关参数', trigger: 'blur' },
          { min: 11, max: 11, message: '长度为 11 个字符', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        departmentid: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        joblevelid: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        posid: [{ required: true, message: '请输入相关参数', trigger: 'blur' }],
        engageform: '劳动合同',
        tiptopdegree: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        specialty: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        school: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        begindate: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        contractterm: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        conversiontime: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        begincontract: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        endcontract: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        idcard: [
          { required: true, message: '请输入相关参数', trigger: 'blur' }
        ],
        wedlock: '已婚'
      },
      // 添加还是编辑
      isEditOrAdd: 'edit',
      titleIsEOrA: '添加用户',
      loading: true,
      // 上传文件小icon
      uploadIcon: 'el-icon-upload',
      uploadIsDis: false,
      serchData: {
        politicid: null,
        nationid: null,
        departmentid: null,
        joblevelid: null,
        posid: null,
        engageform: null,
        conversiontime: null,
        begindate: null
      },
      value2: [],
      isVisabledToTipSearch: false,
      serchDisabled: false
    }
  },
  methods: {
    tipSerchBtn() {
      const obj = {}
      for (const o in this.serchData) {
        if (this.serchData[o] !== null) {
          obj[o] = this.serchData[o]
        }
      }
      if (this.input !== '' && this.input !== null) {
        obj.name = this.input
      }
      this.initTipDataSer(obj)
    },
    initTipDataSer(obj) {
      this.postRequest(
        '/personnel/emp/top/search/' + this.pageNum + '/' + this.pageSize,
        obj
      ).then(resp => {
        this.tableData = resp.data.employees
        this.total = resp.data.total
      })
    },
    uploadError() {
      this.uploadIcon = 'el-icon-upload'
      this.$notify.error({
        title: '错误',
        message: '上传失败！'
      })
      this.uploadIsDis = false
      this.init()
    },
    uploadSuccess() {
      this.uploadIcon = 'el-icon-upload'
      this.$notify({
        title: '成功',
        message: '上传成功！',
        type: 'success'
      })
      this.uploadIsDis = false
      this.init()
    },
    beforeUpload() {
      this.uploadIcon = 'el-icon-loading'
      this.uploadIsDis = true
    },
    serch() {
      this.getRequest('/personnel/emp' + this.input).then(res => {
        this.tableData = res.data.obj
      })
    },
    tipSerch() {
      if (!this.isVisabledToTipSearch) {
        this.initBaseData()
      }
      this.isVisabledToTipSearch = !this.isVisabledToTipSearch
    },
    cancelTipSearch() {
      this.isVisabledToTipSearch = false
    },
    handleClick(item) {
      this.isEditOrAdd = 'edit'
      this.initBaseData()
      this.empform.id = item.id
      this.empform.name = item.name
      this.empform.gender = item.gender
      this.empform.birthday = new Date(item.birthday)
      this.empform.politicid = item.politicid
      this.empform.nationid = item.nationid + ''
      this.empform.nativeplace = item.nativeplace
      this.empform.email = item.email
      this.empform.phone = item.phone
      this.empform.address = item.address
      this.empform.departmentid = item.departmentid
      this.empform.joblevelid = item.joblevelid
      this.empform.posid = item.posid
      this.empform.engageform = item.engageform
      this.empform.tiptopdegree = item.tiptopdegree
      this.empform.specialty = item.specialty
      this.empform.school = item.school
      this.empform.conversiontime = new Date(item.conversiontime)
      this.empform.begincontract = new Date(item.begincontract)
      this.empform.endcontract = new Date(item.endcontract)
      this.empform.idcard = item.idcard
      this.empform.wedlock = item.wedlock
      this.empform.begindate = new Date(item.begindate)
      this.empform.workid = item.workid
      this.dialogVisible = true
    },
    imporT() {},
    exporT() {
      window.open('/personnel/emp/export', '_parent')
    },
    showAddDia() {
      this.dialogVisible = true
      this.isEditOrAdd = 'add'
      this.initBaseData()
    },
    initBaseData() {
      this.getRequest('/system/basic/department/all').then(res => {
        this.departmentData = res.data.obj
      })
      this.getRequest('/system/basic/jobName/').then(res => {
        this.jobNameData = res.data.obj
      })
      this.getRequest('/system/basic/job/').then(res => {
        this.jobLeveData = res.data.obj
      })
      if (this.isEditOrAdd === 'add') {
        this.getRequest('/personnel/emp/max/workid').then(res => {
          this.empform.workid = res.data.obj
        })
      }
    },
    init() {
      this.getRequest(
        '/personnel/emp/' + this.pageNum + '/' + this.pageSize
      ).then(res => {
        this.tableData = res.data.obj.list
        this.total = res.data.obj.total
        this.loading = false
      })
    },
    handleSizeChange(newSize) {
      this.pageSize = newSize
      if (!this.isVisabledToTipSearch) {
        this.init()
        return
      }
      this.tipSerchBtn()
    },
    handleCurrentChange(newNum) {
      this.pageNum = newNum
      if (!this.isVisabledToTipSearch) {
        this.init()
        return
      }
      this.tipSerchBtn()
    },
    Adduser() {
      if (this.isEditOrAdd === 'add') {
        this.$refs.empformRef.validate(vali => {
          if (!vali) return this.$message.error('请正确填写必填项！')
          this.putRequest('/personnel/emp/one', this.empform).then(res => {
            this.$message.success(res.data.msg)
          })
          this.dialogVisible = false
          this.$refs.empformRef.resetFields()
        })
      } else if (this.isEditOrAdd === 'edit') {
        this.putRequest('/personnel/emp/change', this.empform).then(res => {
          this.$message.success(res.data.msg)
          this.resetData()
          this.init()
          this.dialogVisible = false
        })
      }
    },
    cancel() {
      this.resetData()
      this.dialogVisible = false
    },
    resetData() {
      this.empform.id = ''
      this.empform.name = ''
      this.empform.gender = '男'
      this.empform.birthday = ''
      this.empform.politicid = ''
      this.empform.nationid = ''
      this.empform.nativeplace = ''
      this.empform.email = ''
      this.empform.phone = ''
      this.empform.address = ''
      this.empform.departmentid = ''
      this.empform.joblevelid = ''
      this.empform.posid = ''
      this.empform.engageform = '劳动合同'
      this.empform.tiptopdegree = ''
      this.empform.specialty = ''
      this.empform.school = ''
      this.empform.conversiontime = ''
      this.empform.begincontract = ''
      this.empform.endcontract = ''
      this.empform.idcard = ''
      this.empform.wedlock = '已婚'
      this.empform.workid = ''
      this.empform.begindate = ''
    },
    remove(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteRequest('/personnel/emp/' + id).then(res => {
            this.$message.success(res.data.msg)
            this.init()
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }
  },
  created() {
    this.init()
  },
  watch: {
    input(val) {
      if (val === '') {
        this.init()
      }
    },
    isEditOrAdd(val) {
      if (val === 'add') {
        this.titleIsEOrA = '添加用户'
      } else {
        this.titleIsEOrA = '修改用户'
      }
    },
    value2(val) {
      this.serchData.begindate = val[0]
      this.serchData.conversiontime = val[1]
    },
    isVisabledToTipSearch(val) {
      this.serchDisabled = !this.serchDisabled
      if (!val) {
        this.init()
      }
    }
  }
}
</script>

<style lang="less" scoped>
.el-card {
  margin-top: 15px;
}
.el-button {
  margin-left: 15px;
}
.top {
  display: flex;
  justify-content: space-between;
}
body {
  margin: 0;
}
.rightBtn {
  display: flex;
}
.slide-fade-enter-active {
  transition: all 0.3s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>
