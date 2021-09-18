package com.bjke.flink.domain;

public class RecordDetailsV2 {
    public Integer id;
    public String created_at;
    public String updated_at;
    public String deleted_at;
    public Long date;
    public String player_id;
    public String player_name;
    public String seat;
    public String roleId;
    public String choose;
    public String power_wolf;
    public String operation_id;
    public String day;
    public String season_id;
    public String group;
    public String score;
    public String points;
    public String egg;
    public String active_score;
    public String total;
    public String suicide;
    public String bottom;
    public String topVote;
    public String record_id;
    public String season_type_id;
    public String deaths_id;
    public String round;
    public String dateDay;
    public String hour;

    public RecordDetailsV2(Integer id) {
        this.id = id;
    }

    public RecordDetailsV2(Integer id, String created_at, String updated_at, String deleted_at, Long date, String player_id, String player_name, String seat, String roleId, String choose, String power_wolf, String operation_id, String day, String season_id, String group, String score, String points, String egg, String active_score, String total, String suicide, String bottom, String topVote, String record_id, String season_type_id, String deaths_id, String round, String dateDay, String hour) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.date = date;
        this.player_id = player_id;
        this.player_name = player_name;
        this.seat = seat;
        this.roleId = roleId;
        this.choose = choose;
        this.power_wolf = power_wolf;
        this.operation_id = operation_id;
        this.day = day;
        this.season_id = season_id;
        this.group = group;
        this.score = score;
        this.points = points;
        this.egg = egg;
        this.active_score = active_score;
        this.total = total;
        this.suicide = suicide;
        this.bottom = bottom;
        this.topVote = topVote;
        this.record_id = record_id;
        this.season_type_id = season_type_id;
        this.deaths_id = deaths_id;
        this.round = round;
        this.dateDay = dateDay;
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "RecordDetailsV2{" +
                "id=" + id +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                ", date=" + date +
                ", player_id='" + player_id + '\'' +
                ", player_name='" + player_name + '\'' +
                ", seat='" + seat + '\'' +
                ", roleId='" + roleId + '\'' +
                ", choose='" + choose + '\'' +
                ", power_wolf='" + power_wolf + '\'' +
                ", operation_id='" + operation_id + '\'' +
                ", day='" + day + '\'' +
                ", season_id='" + season_id + '\'' +
                ", group='" + group + '\'' +
                ", score='" + score + '\'' +
                ", points='" + points + '\'' +
                ", egg='" + egg + '\'' +
                ", active_score='" + active_score + '\'' +
                ", total='" + total + '\'' +
                ", suicide='" + suicide + '\'' +
                ", bottom='" + bottom + '\'' +
                ", topVote='" + topVote + '\'' +
                ", record_id='" + record_id + '\'' +
                ", season_type_id='" + season_type_id + '\'' +
                ", deaths_id='" + deaths_id + '\'' +
                ", round='" + round + '\'' +
                ", dateDay='" + dateDay + '\'' +
                ", hour='" + hour + '\'' +
                '}';
    }
}
