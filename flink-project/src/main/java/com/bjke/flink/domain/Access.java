package com.bjke.flink.domain;

/**
 * {
 *       "event": "$AppStart",
 *       "user_id": "-8.91903E+18",
 *       "distinct_id": "2579707",
 *       "date": "2019/6/14",
 *       "time": "16:10.2",
 *       "$manufacturer": "Apple",
 *       "$model": "iPhone10,2",
 *       "$os": "iOS",
 *       "$os_version": "12.3.1",
 *       "$app_version": "4.5.22",
 *       "$wifi": "1",
 *       "$ip": "121.69.135.49",
 *       "$province": "北京",
 *       "$city": "北京",
 *       "$screen_width": "375",
 *       "uid": "2579707",
 *       "ip": "",
 *       "create_time": "",
 *       "is_first_visit": "",
 *       "city": "bj",
 *       "username": "13810636452",
 *       "first_time": "",
 *       "house_id": "",
 *       "borough_id": "",
 *       "gov_id": "",
 *       "$country": "中国",
 *       "$carrier": "中国移动",
 *       "$lib": "iOS",
 *       "$network_type": "WIFI",
 *       "$lib_version": "1.11.1",
 *       "$screen_name": "",
 *       "$is_first_time": "0",
 *       "$resume_from_background": "0",
 *       "platform": "3",
 *       "appname": "zgzf",
 *       "selectcity": "bj",
 *       "loginstatus": "强登陆",
 *       "$is_first_day": "0",
 *       "$title": "",
 *       "qudao": "",
 *       "$device_id": "B7BDD202-7362-4D3A-9ECD-EF264E642D52",
 *       "$track_signup_original_id": "",
 *       "newhouse_username": "",
 *       "cityarea2_name": "",
 *       "kwd": "",
 *       "pid": "",
 *       "unitid": "",
 *       "user_type": "强登录",
 *       "user_phone": "13810636452",
 *       "device_idfa_imei": "B7BDD202-7362-4D3A-9ECD-EF264E642D52",
 *       "deviceid": "B7BDD202-7362-4D3A-9ECD-EF264E642D52",
 *       "project": "",
 *       "crtate_time": "",
 *       "app_name": "zgzf",
 *       "app_version": "",
 *       "wechat_id": "",
 *       "device_id": "B7BDD202-7362-4D3A-9ECD-EF264E642D52",
 *       "model": "iPhone 8 Plus",
 *       "manufacturer": "Apple",
 *       "os": "iOS",
 *       "os_version": "12.3.1",
 *       "wechat_version": "",
 *       "network_type": "",
 *       "select_city": "bj",
 *       "carrier": "中国移动",
 *       "$kafka_offset": "26002853200",
 *       "version": "4.5.2.2",
 *       "oaid": "",
 *       "$timezone_offset": "",
 *       "$app_name": "",
 *       "$app_id": "",
 *       "$brand": ""
 *     }
 */
public class Access {
    private String event;

    private String user_id;

    private String distinct_id;

    private String date;

    private String time;

    private String $manufacturer;

    private String $model;

    private String $os;

    private String $os_version;

    private String $app_version;

    private String $wifi;

    private String $ip;

    private String $province;

    private String $city;

    private String $screen_width;

    private String uid;

    private String ip;

    private String create_time;

    private String is_first_visit;

    private String city;

    private String username;

    private String first_time;

    private String house_id;

    private String borough_id;

    private String gov_id;

    private String $country;

    private String $carrier;

    private String $lib;

    private String $network_type;

    private String $lib_version;

    private String $screen_name;

    private String $is_first_time;

    private String $resume_from_background;

    private String platform;

    private String appname;

    private String selectcity;

    private String loginstatus;

    private String $is_first_day;

    private String $title;

    private String qudao;

    private String $device_id;

    private String $track_signup_original_id;

    private String newhouse_username;

    private String cityarea2_name;

    private String kwd;

    private String pid;

    private String unitid;

    private String user_type;

    private String user_phone;

    private String device_idfa_imei;

    private String deviceid;

    private String project;

    private String crtate_time;

    private String app_name;

    private String app_version;

    private String wechat_id;

    private String device_id;

    private String model;

    private String manufacturer;

    private String os;

    private String os_version;

    private String wechat_version;

    private String network_type;

    private String select_city;

    private String carrier;

    private String $kafka_offset;

    private String version;

    private String oaid;

    private String $timezone_offset;

    private String $app_name;

    private String $app_id;

    private String $brand;

    public Access(String event, String user_id, String distinct_id, String date, String time, String $manufacturer, String $model, String $os, String $os_version, String $app_version, String $wifi, String $ip, String $province, String $city, String $screen_width, String uid, String ip, String create_time, String is_first_visit, String city, String username, String first_time, String house_id, String borough_id, String gov_id, String $country, String $carrier, String $lib, String $network_type, String $lib_version, String $screen_name, String $is_first_time, String $resume_from_background, String platform, String appname, String selectcity, String loginstatus, String $is_first_day, String $title, String qudao, String $device_id, String $track_signup_original_id, String newhouse_username, String cityarea2_name, String kwd, String pid, String unitid, String user_type, String user_phone, String device_idfa_imei, String deviceid, String project, String crtate_time, String app_name, String app_version, String wechat_id, String device_id, String model, String manufacturer, String os, String os_version, String wechat_version, String network_type, String select_city, String carrier, String $kafka_offset, String version, String oaid, String $timezone_offset, String $app_name, String $app_id, String $brand) {
        this.event = event;
        this.user_id = user_id;
        this.distinct_id = distinct_id;
        this.date = date;
        this.time = time;
        this.$manufacturer = $manufacturer;
        this.$model = $model;
        this.$os = $os;
        this.$os_version = $os_version;
        this.$app_version = $app_version;
        this.$wifi = $wifi;
        this.$ip = $ip;
        this.$province = $province;
        this.$city = $city;
        this.$screen_width = $screen_width;
        this.uid = uid;
        this.ip = ip;
        this.create_time = create_time;
        this.is_first_visit = is_first_visit;
        this.city = city;
        this.username = username;
        this.first_time = first_time;
        this.house_id = house_id;
        this.borough_id = borough_id;
        this.gov_id = gov_id;
        this.$country = $country;
        this.$carrier = $carrier;
        this.$lib = $lib;
        this.$network_type = $network_type;
        this.$lib_version = $lib_version;
        this.$screen_name = $screen_name;
        this.$is_first_time = $is_first_time;
        this.$resume_from_background = $resume_from_background;
        this.platform = platform;
        this.appname = appname;
        this.selectcity = selectcity;
        this.loginstatus = loginstatus;
        this.$is_first_day = $is_first_day;
        this.$title = $title;
        this.qudao = qudao;
        this.$device_id = $device_id;
        this.$track_signup_original_id = $track_signup_original_id;
        this.newhouse_username = newhouse_username;
        this.cityarea2_name = cityarea2_name;
        this.kwd = kwd;
        this.pid = pid;
        this.unitid = unitid;
        this.user_type = user_type;
        this.user_phone = user_phone;
        this.device_idfa_imei = device_idfa_imei;
        this.deviceid = deviceid;
        this.project = project;
        this.crtate_time = crtate_time;
        this.app_name = app_name;
        this.app_version = app_version;
        this.wechat_id = wechat_id;
        this.device_id = device_id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.os = os;
        this.os_version = os_version;
        this.wechat_version = wechat_version;
        this.network_type = network_type;
        this.select_city = select_city;
        this.carrier = carrier;
        this.$kafka_offset = $kafka_offset;
        this.version = version;
        this.oaid = oaid;
        this.$timezone_offset = $timezone_offset;
        this.$app_name = $app_name;
        this.$app_id = $app_id;
        this.$brand = $brand;
    }

    public Access() {
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDistinct_id() {
        return distinct_id;
    }

    public void setDistinct_id(String distinct_id) {
        this.distinct_id = distinct_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String get$manufacturer() {
        return $manufacturer;
    }

    public void set$manufacturer(String $manufacturer) {
        this.$manufacturer = $manufacturer;
    }

    public String get$model() {
        return $model;
    }

    public void set$model(String $model) {
        this.$model = $model;
    }

    public String get$os() {
        return $os;
    }

    public void set$os(String $os) {
        this.$os = $os;
    }

    public String get$os_version() {
        return $os_version;
    }

    public void set$os_version(String $os_version) {
        this.$os_version = $os_version;
    }

    public String get$app_version() {
        return $app_version;
    }

    public void set$app_version(String $app_version) {
        this.$app_version = $app_version;
    }

    public String get$wifi() {
        return $wifi;
    }

    @Override
    public String toString() {
        return "Access{" +
                "event='" + event + '\'' +
                ", user_id='" + user_id + '\'' +
                ", distinct_id='" + distinct_id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", $manufacturer='" + $manufacturer + '\'' +
                ", $model='" + $model + '\'' +
                ", $os='" + $os + '\'' +
                ", $os_version='" + $os_version + '\'' +
                ", $app_version='" + $app_version + '\'' +
                ", $wifi='" + $wifi + '\'' +
                ", $ip='" + $ip + '\'' +
                ", $province='" + $province + '\'' +
                ", $city='" + $city + '\'' +
                ", $screen_width='" + $screen_width + '\'' +
                ", uid='" + uid + '\'' +
                ", ip='" + ip + '\'' +
                ", create_time='" + create_time + '\'' +
                ", is_first_visit='" + is_first_visit + '\'' +
                ", city='" + city + '\'' +
                ", username='" + username + '\'' +
                ", first_time='" + first_time + '\'' +
                ", house_id='" + house_id + '\'' +
                ", borough_id='" + borough_id + '\'' +
                ", gov_id='" + gov_id + '\'' +
                ", $country='" + $country + '\'' +
                ", $carrier='" + $carrier + '\'' +
                ", $lib='" + $lib + '\'' +
                ", $network_type='" + $network_type + '\'' +
                ", $lib_version='" + $lib_version + '\'' +
                ", $screen_name='" + $screen_name + '\'' +
                ", $is_first_time='" + $is_first_time + '\'' +
                ", $resume_from_background='" + $resume_from_background + '\'' +
                ", platform='" + platform + '\'' +
                ", appname='" + appname + '\'' +
                ", selectcity='" + selectcity + '\'' +
                ", loginstatus='" + loginstatus + '\'' +
                ", $is_first_day='" + $is_first_day + '\'' +
                ", $title='" + $title + '\'' +
                ", qudao='" + qudao + '\'' +
                ", $device_id='" + $device_id + '\'' +
                ", $track_signup_original_id='" + $track_signup_original_id + '\'' +
                ", newhouse_username='" + newhouse_username + '\'' +
                ", cityarea2_name='" + cityarea2_name + '\'' +
                ", kwd='" + kwd + '\'' +
                ", pid='" + pid + '\'' +
                ", unitid='" + unitid + '\'' +
                ", user_type='" + user_type + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", device_idfa_imei='" + device_idfa_imei + '\'' +
                ", deviceid='" + deviceid + '\'' +
                ", project='" + project + '\'' +
                ", crtate_time='" + crtate_time + '\'' +
                ", app_name='" + app_name + '\'' +
                ", app_version='" + app_version + '\'' +
                ", wechat_id='" + wechat_id + '\'' +
                ", device_id='" + device_id + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", os='" + os + '\'' +
                ", os_version='" + os_version + '\'' +
                ", wechat_version='" + wechat_version + '\'' +
                ", network_type='" + network_type + '\'' +
                ", select_city='" + select_city + '\'' +
                ", carrier='" + carrier + '\'' +
                ", $kafka_offset='" + $kafka_offset + '\'' +
                ", version='" + version + '\'' +
                ", oaid='" + oaid + '\'' +
                ", $timezone_offset='" + $timezone_offset + '\'' +
                ", $app_name='" + $app_name + '\'' +
                ", $app_id='" + $app_id + '\'' +
                ", $brand='" + $brand + '\'' +
                '}';
    }

    public void set$wifi(String $wifi) {
        this.$wifi = $wifi;
    }

    public String get$ip() {
        return $ip;
    }

    public void set$ip(String $ip) {
        this.$ip = $ip;
    }

    public String get$province() {
        return $province;
    }

    public void set$province(String $province) {
        this.$province = $province;
    }

    public String get$city() {
        return $city;
    }

    public void set$city(String $city) {
        this.$city = $city;
    }

    public String get$screen_width() {
        return $screen_width;
    }

    public void set$screen_width(String $screen_width) {
        this.$screen_width = $screen_width;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_first_visit() {
        return is_first_visit;
    }

    public void setIs_first_visit(String is_first_visit) {
        this.is_first_visit = is_first_visit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_time() {
        return first_time;
    }

    public void setFirst_time(String first_time) {
        this.first_time = first_time;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getBorough_id() {
        return borough_id;
    }

    public void setBorough_id(String borough_id) {
        this.borough_id = borough_id;
    }

    public String getGov_id() {
        return gov_id;
    }

    public void setGov_id(String gov_id) {
        this.gov_id = gov_id;
    }

    public String get$country() {
        return $country;
    }

    public void set$country(String $country) {
        this.$country = $country;
    }

    public String get$carrier() {
        return $carrier;
    }

    public void set$carrier(String $carrier) {
        this.$carrier = $carrier;
    }

    public String get$lib() {
        return $lib;
    }

    public void set$lib(String $lib) {
        this.$lib = $lib;
    }

    public String get$network_type() {
        return $network_type;
    }

    public void set$network_type(String $network_type) {
        this.$network_type = $network_type;
    }

    public String get$lib_version() {
        return $lib_version;
    }

    public void set$lib_version(String $lib_version) {
        this.$lib_version = $lib_version;
    }

    public String get$screen_name() {
        return $screen_name;
    }

    public void set$screen_name(String $screen_name) {
        this.$screen_name = $screen_name;
    }

    public String get$is_first_time() {
        return $is_first_time;
    }

    public void set$is_first_time(String $is_first_time) {
        this.$is_first_time = $is_first_time;
    }

    public String get$resume_from_background() {
        return $resume_from_background;
    }

    public void set$resume_from_background(String $resume_from_background) {
        this.$resume_from_background = $resume_from_background;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getSelectcity() {
        return selectcity;
    }

    public void setSelectcity(String selectcity) {
        this.selectcity = selectcity;
    }

    public String getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(String loginstatus) {
        this.loginstatus = loginstatus;
    }

    public String get$is_first_day() {
        return $is_first_day;
    }

    public void set$is_first_day(String $is_first_day) {
        this.$is_first_day = $is_first_day;
    }

    public String get$title() {
        return $title;
    }

    public void set$title(String $title) {
        this.$title = $title;
    }

    public String getQudao() {
        return qudao;
    }

    public void setQudao(String qudao) {
        this.qudao = qudao;
    }

    public String get$device_id() {
        return $device_id;
    }

    public void set$device_id(String $device_id) {
        this.$device_id = $device_id;
    }

    public String get$track_signup_original_id() {
        return $track_signup_original_id;
    }

    public void set$track_signup_original_id(String $track_signup_original_id) {
        this.$track_signup_original_id = $track_signup_original_id;
    }

    public String getNewhouse_username() {
        return newhouse_username;
    }

    public void setNewhouse_username(String newhouse_username) {
        this.newhouse_username = newhouse_username;
    }

    public String getCityarea2_name() {
        return cityarea2_name;
    }

    public void setCityarea2_name(String cityarea2_name) {
        this.cityarea2_name = cityarea2_name;
    }

    public String getKwd() {
        return kwd;
    }

    public void setKwd(String kwd) {
        this.kwd = kwd;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getDevice_idfa_imei() {
        return device_idfa_imei;
    }

    public void setDevice_idfa_imei(String device_idfa_imei) {
        this.device_idfa_imei = device_idfa_imei;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCrtate_time() {
        return crtate_time;
    }

    public void setCrtate_time(String crtate_time) {
        this.crtate_time = crtate_time;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getWechat_id() {
        return wechat_id;
    }

    public void setWechat_id(String wechat_id) {
        this.wechat_id = wechat_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getWechat_version() {
        return wechat_version;
    }

    public void setWechat_version(String wechat_version) {
        this.wechat_version = wechat_version;
    }

    public String getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(String network_type) {
        this.network_type = network_type;
    }

    public String getSelect_city() {
        return select_city;
    }

    public void setSelect_city(String select_city) {
        this.select_city = select_city;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String get$kafka_offset() {
        return $kafka_offset;
    }

    public void set$kafka_offset(String $kafka_offset) {
        this.$kafka_offset = $kafka_offset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    public String get$timezone_offset() {
        return $timezone_offset;
    }

    public void set$timezone_offset(String $timezone_offset) {
        this.$timezone_offset = $timezone_offset;
    }

    public String get$app_name() {
        return $app_name;
    }

    public void set$app_name(String $app_name) {
        this.$app_name = $app_name;
    }

    public String get$app_id() {
        return $app_id;
    }

    public void set$app_id(String $app_id) {
        this.$app_id = $app_id;
    }

    public String get$brand() {
        return $brand;
    }

    public void set$brand(String $brand) {
        this.$brand = $brand;
    }
}

