## 概要
IncidentAppは、医療現場で発生するインシデントを記録し、管理するためのWebアプリケーションです。ユーザーはインシデントの新規作成、編集、削除ができ、発生状況や原因、対応策などを入力することができます。また、インシデントのカテゴリー別、レベル別、所属科別、職種別の集計グラフを表示する機能も備えています。

## 特徴
- インシデントの新規作成、編集、削除
- インシデントの検索機能
- カテゴリー別、レベル別、所属科別、職種別の集計グラフ表示
- 管理者パスワードによる削除機能
- OpenAI APIを使用した原因の提案機能

CREATE TABLE IF NOT EXISTS incidents (
id                 INT       NOT NULL AUTO_INCREMENT,
   level              VARCHAR(256) NOT NULL,
   date               VARCHAR(256) NOT NULL,
   time               VARCHAR(256) NOT NULL,
   place              VARCHAR(256) NOT NULL,
   number             VARCHAR(256) NOT NULL,
   name               VARCHAR(256) NOT NULL,
   age                VARCHAR(256) NOT NULL,
   department         VARCHAR(256) NOT NULL,
   job                VARCHAR(256) NOT NULL,
   continuation       VARCHAR(256) NOT NULL,
   category           VARCHAR(256) NOT NULL,
   segment            VARCHAR(256) NOT NULL,
   situation          VARCHAR(256) NOT NULL,
   cause              VARCHAR(256) NOT NULL,
   suggestion         VARCHAR(256) ,
   countermeasure     VARCHAR(256) ,

PRIMARY KEY (id)
);