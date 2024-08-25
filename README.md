# IncidentApp

このプロジェクトは、AWS Elastic Beanstalk 環境にデプロイされた Java アプリケーション「IncidentApp」です。このアプリケーションは、JavaFX を使用して作成され、MySQL データベースと接続しています。


### 1. プロジェクト概要
IncidentAppは、医療の現場で発生するインシデントに対する管理システムで、新規作成、編集、削除できる機能を提供します。\
インシデントレポートの一覧表示やグラフ表示機能も備えています。\
新規作成・編集画面ではコンボボックス機能を用い、コンボボックスで2段階の選択方式にする事で入力しやすいシステムとしています。\
OpenAI機能を兼ね備え、対策の提案を行う事で専従での医療安全管理責任者ではなく、兼任で担当されている施設でも的確な対策を講じる事ができます。

### 2. アプリケーションを作成した背景
医療現場でのインシデントは、時に患者の生命や機能に影響を及ぼすことがあります。ここで言うインシデントとは、実際には事なきを得ているものの、事件や事故につながりかねない状態を指します。既存の医療安全を管理するソフトウェアは存在しますが、高額なため、Excelや紙ベースで管理している施設がある現状です。このため、医療安全の状況を把握・改善するための効率的な管理システムの必要性を感じていました。\
私は、医療安全責任者としての業務を兼務した経験があり、Excelを用いて管理してきました。その際、インシデントの発生状況をグラフにて視覚的に一目で把握できるソフトを求めていました。また、大学病院などの大規模病院では医療安全管理を専従で担当している場合が多いですが、ほとんどの医療機関では兼務で担当しています。専従であれば適切な対策を講じることができますが、兼務の場合は日々の業務を遂行しつつ医療安全の対策を考える必要があるため、業務負担が大きくなります。\
そこで、Open AIの機能を活用して提案を行う機能があれば、兼務で対応している施設でも的確な対策を実施できると考え、このアプリケーションを作成しました。

### 3.要件定義
| 機能            　　　　　  | 目的                                 | 詳細                                                                                                                       　　　 |
|----------------------------|--------------------------------------| -------------------------------------------------------------------------------------------------------------------------------- |
| インシデント管理機能        | インシデントの登録、編集、削除を行う   | インシデントの登録画面、編集画面では、大分類と小分類の2段階コンボボックスを利用。削除には管理者パスワードを入力することで実行可能。　　　|
| インシデント一覧表示機能    | インシデントの視覚的な管理             |登録されたインシデントをリスト形式で表示し、検索機能を利用して特定のインシデントを簡単に見つけることができる。                    　　　 |
| グラフ表示機能             | インシデントデータの視覚的な分析        |インシデントデータを集計し、円グラフで表示する。                                                                             　　　  |
| OpenAI提案機能             | 効果的なインシデント対策の提案を支援    |インシデントの状況に基づいて、OpenAIを活用し、対応策や改善案の提案を生成する事で適切な対策を講じる事ができる。                  　　　  |
| 管理者ログイン機能          | インシデントのセキュアな管理           |管理者のみがインシデントの削除や編集を行えるように、ログイン機能を提供。                                                      　　　  |
| MySQLデータベース接続       | 安定したデータベース接続とデータの管理 |AWS RDSのMySQLデータベースと接続し、インシデントデータの保存・読み込みを行う                                                  　　　  |
| クラウドデプロイ機能        | システムの可用性を向上                 |AWS Elastic Beanstalkを利用してシステムをクラウドにデプロイし、自動スケーリングと高可用性を確保する。                          　　　 |
| 集計機能     　　　　　　   | 表機能により分類別件数を把握           | 表で分類別件数を表示する事により、インシデントからの改善が必要なカテゴリーや所属科・職種の把握ができます。また、過去との比較も可能です。|

### 4.管理者パスワード
インシデントを削除する際に管理者用パスワードを使用します。\
パスワード: admin

### 5.利用方法
ホーム画面で インシデントの新規作成・編集・削除を行います。\
ホーム画面では作成されたインシデントに対して4種類のグラフが表示されます。\
ホーム画面で検索機能を用いる事により必要なデータのみを表示する事ができます。\
削除する際はパスワードを問うポップアップが表示され、合致するとインシデントの削除が出来ます。\
新規作成画面の分類は2段階で入力し、コンボボックスの設定で大分類に対して小分類で選択肢が変化します。\
新規作成画面・編集画面ではOpenAI機能を使用して、インシデントの対策案の提案を生成することができます。\
年次集計画面により、レベル・カテゴリー・所属科・職種別で発生頻度の多い事項を把握でき、改善点を明らかにします。\
年次集計画面に検索機能を追加する事で、過去のインシデント状況と比較ができます。

### 6.開発環境
IntelliJ IDEA \
Windows 10 \
Docker（開発用）\
MySQL（開発用）

### 7.動作方法
http://IncidentApp-env-1.eba-y4zaacyt.ap-northeast-1.elasticbeanstalk.com  \
上記URLにアクセスする事で動作できます。


### 8.工夫したポイント
JavaFXによる直感的なユーザーインターフェースの実装。\
OpenAI APIを利用した提案機能の追加。\
AWS Elastic Beanstalkによるデプロイ。


## IncidentApp データベース設計
### incidents テーブル
| Colum          | Type    | Options                     |
|----------------|---------| --------------------------- |
| level          | int     | null: false                 |
| date           | date    | null: false                 |
| time           | time    | null: false                 |
| place          | Varchar | null: false                 |
| number         | Varchar | null: false                 |
| name           | Varchar | null: false                 |
| age            | int     | null: false                 |
| department     | Varchar | null: false                 |
| job            | Varchar | null: false                 |
| continuation   | int     | null: false                 |
| category       | Varchar | null: false                 |
| segment        | Varchar | null: false                 |
| situation      | text    | null: false                 |
| cause          | text    | null: false                 |
| suggestion     | text    |                             |
| countermeasure | text    |                             |

### Association
- (none, since this is a standalone table)

## 画面遷移図
[![Image from Gyazo](https://i.gyazo.com/869a7872c8cb5b36f9990871aa0c451d.png)](https://gyazo.com/869a7872c8cb5b36f9990871aa0c451d)

