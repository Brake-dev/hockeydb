BEGIN;

CREATE TABLE season (
    season_id           uuid PRIMARY KEY,
    name                char(9)             -- 2023-2024
);

CREATE TABLE team (
    team_id             uuid PRIMARY KEY,
    name                varchar(24) NOT NULL,
    division            varchar(12),
    conference          char(7)             -- Eastern, Western
);

CREATE TABLE team_seasons (
    team_id             uuid REFERENCES team,
    season_id           uuid REFERENCES season,
    PRIMARY KEY (team_id, season_id)
);

CREATE TABLE team_stats (
    team_stats_id       uuid PRIMARY KEY,
    team_id             uuid REFERENCES team,
    season_id           uuid REFERENCES season,
    games               integer,
    wins                integer,
    losses              integer,
    overtime_wins       integer,
    overtime_losses     integer,
    points              integer,
    goals_for           integer,
    goals_against       integer,
    home_record         varchar(8),          -- 11-4-2
    away_record         varchar(8),          -- 8-1-2
    shootout_record     varchar(8),          -- 2-0
    last_10             varchar(8),          -- 7-2-1
    streak              varchar(3)           -- W2
);

CREATE TABLE skater (
    skater_id           uuid PRIMARY KEY,
    name                varchar(64) NOT NULL,
    number              integer,
    height              varchar(5),         -- 5′11″
    weight              varchar(6),         -- 196 lb
    born                date,               -- 11/6/1995
    birthplace          varchar(64),        -- West Vancouver, British Columbia, CAN
    shoots              char(1),            -- L
    draft               varchar(64),        -- 2014, BUF (2nd overall), 1st round, 2nd pick
    position            varchar(2)          -- LW
);

CREATE TABLE skater_seasons (
    skater_id           uuid REFERENCES skater,
    season_id           uuid REFERENCES season,
    PRIMARY KEY (skater_id, season_id)
);

CREATE TABLE skater_stats (
    skater_stats_id         uuid PRIMARY KEY,
    skater_id               uuid REFERENCES skater,
    season_id               uuid REFERENCES season,
    games_played            integer,
    goals                   integer,
    assists                 integer,
    points                  integer,
    plus_minus              integer,
    penalty_minutes         integer,
    powerplay_goals         integer,
    powerplay_points        integer,
    shorthanded_goals       integer,
    shorthanded_points      integer,
    time_on_ice_per_gp      integer,    -- Seconds
    game_winning_goals      integer,
    overtime_goals          integer,
    shots_on_goal           integer,
    face_off_percentage     real
);

CREATE TABLE goalie (
    goalie_id           uuid PRIMARY KEY,
    name                varchar(64) NOT NULL,
    number              integer,
    height              varchar(5),
    weight              varchar(6),
    born                date,
    birthplace          varchar(64),
    catches             char(1),             -- L
    draft               varchar(64)
);

CREATE TABLE goalie_seasons (
    goalie_id               uuid REFERENCES goalie,
    season_id               uuid REFERENCES season,
    PRIMARY KEY (goalie_id, season_id)
);

CREATE TABLE goalie_stats (
    goalie_stats_id         uuid PRIMARY KEY,
    goalie_id               uuid REFERENCES goalie,
    season_id               uuid REFERENCES season,
    games_played            integer,
    games_started           integer,
    wins                    integer,
    losses                  integer,
    overtime_losses         integer,
    shots_against           integer,
    goals_against_average   real,
    save_percentage         real,
    shutouts                integer,
    goals                   integer,
    assists                 integer,
    penalty_minutes         integer,
    time_on_ice             integer     -- Seconds
);

CREATE TABLE team_skaters (
    team_id                 uuid REFERENCES team,
    skater_id               uuid REFERENCES skater,
    season_id               uuid REFERENCES season,
    PRIMARY KEY (team_id, skater_id, season_id)
);

CREATE TABLE team_goalies (
    team_id                 uuid REFERENCES team,
    goalie_id               uuid REFERENCES goalie,
    season_id               uuid REFERENCES season,
    PRIMARY KEY (team_id, goalie_id, season_id)
);


CREATE TABLE game (
    game_id                     uuid PRIMARY KEY,
    season_id                   uuid REFERENCES season,
    home_team                   uuid REFERENCES team,
    away_team                   uuid REFERENCES team,
    overtime                    boolean,
    shootout                    boolean,

    home_team_goals             integer,
    home_team_shots_on_goal     integer,
    home_team_face_off_percent  real,
    home_team_powerplay_percent real,
    home_team_penalty_minutes   integer,
    home_team_hits              integer,
    home_team_blocked_shots     integer,
    home_team_giveaways         integer,
    home_team_takeaways         integer,

    away_team_goals             integer,
    away_team_shots_on_goal     integer,
    away_team_face_off_percent  real,
    away_team_powerplay_percent real,
    away_team_penalty_minutes   integer,
    away_team_hits              integer,
    away_team_blocked_shots     integer,
    away_team_giveaways         integer,
    away_team_takeaways         integer
);

CREATE TABLE team_games (
    game_id                 uuid REFERENCES game,
    team_id                 uuid REFERENCES team,
    season_id               uuid REFERENCES season,
    PRIMARY KEY (game_id, team_id, season_id)
);

COMMIT;