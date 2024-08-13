**# IncidentApp**

このプロジェクトは、AWS Elastic Beanstalk 環境にデプロイされた Java アプリケーション「IncidentApp」です。このアプリケーションは、JavaFX を使用して作成され、MySQL データベースと接続しています。


プロジェクト概要
IncidentAppは、医療の現場で発生するインシデントに対する管理システムで、新規作成、編集、削除できる機能を提供します。
インシデントレポートの一覧表示やグラフ表示機能も備えています。
新規作成・編集画面ではコンボボックス機能を用い、コンボボックスで2段階の選択方式にする事で入力しやすいシステムとしています。
OpenAI機能を兼ね備え、対策の提案を行う事で専従での医療安全管理責任者ではなく、兼任で担当されている施設でも的確な対策を講じる事ができます。

アプリケーションを作成した背景
医療現場でのインシデントは、時に患者の生命や機能に影響を及ぼす事があります。インシデントとは実際には事なきを得ている物の事件や事故に繋がりかねない状態の事を言います。
医療安全を管理するソフトは存在はするものの高額な為、Excelで管理する施設や紙ベースでの管理をしている施設がある現状です。その為、医療安全の状況を把握・改善するための効率的な管理システムの必要性を感じていました。
私は医療安全責任者という責務を兼務で行っており、Excelを用いて管理しておりました。その際、グラフにて視覚的にインシデントの発生状況を一目でわかるソフトが欲しいと感じました。
また、大学病院などの大規模病院では専従で医療安全管理をしているが、ほとんどの医療機関では兼務で担当しています。専従であれば適切な対策を講じる事ができますが、兼務であれば日々の業務を行った上で医療安全の対策を考える必要があるため激務をなります。
その為、Open Ai機能で提案ができる機能があれば兼務で行っている施設でも的確な対策ができると考え、当アプリケーションを作成する事にしました。

主な機能
インシデントの登録、更新、削除
インシデントレポートの一覧表示
インシデントデータのグラフ表示
管理者用ログイン機能
コンボボックス機能
OpenAI機能
環境
言語: Java 17
フレームワーク: JavaFX
データベース: MySQL
ビルドツール: Gradle 8.3
クラウドプロバイダー: AWS Elastic Beanstalk

管理者パスワード
インシデントを削除する際に管理者用パスワードを使用します。
パスワード: admin

利用方法
ホーム画面で インシデントの新規作成・編集・削除を行います。
ホーム画面では作成されたインシデントに対して4種類のグラフが表示されます。
ホーム画面で検索機能を用いる事により必要なデータのみを表示する事ができます。
削除する際はパスワードを問うポップアップが表示され、合致するとインシデントの削除が出来ます。
新規作成画面の分類は2段階で入力し、コンボボックスの設定で大分類に対して小分類で選択肢が変化します。
新規作成画面・編集画面ではOpenAI機能を使用して、インシデントの対策案の提案を生成することができます。

開発環境
IntelliJ IDEA
Windows 10
Docker（開発用）
MySQL（開発用）

動作方法
IncidentApp-env.eba-y4zaacyt.ap-northeast-1.elasticbeanstalk.comにアクセスする事で動作できます。

工夫したポイント
JavaFXによる直感的なユーザーインターフェースの実装。
OpenAI APIを利用した自動提案機能の追加。
AWS Elastic Beanstalkによるデプロイ。
IncidentApp-env.eba-y4zaacyt.ap-northeast-1.elasticbeanstalk.com


# IncidentApp データベース設計
## incidents テーブル
| Colum              | Type    | Options                     |
|--------------------|---------| --------------------------- |
| level              | int     | null: false                 |
| date               | date    | null: false                 |
| time               | time    | null: false                 |
| place              | Varchar | null: false                 |
| patient_id         | Varchar | null: false                 |
| patient_name       | Varchar | null: false                 |
| patient_age        | int     | null: false                 |
| department         | Varchar | null: false                 |
| job                | Varchar | null: false                 |
| continuous_service | int     | null: false                 |
| large_category     | Varchar | null: false                 |
| samll_category     | Varchar | null: false                 |
| situation          | text    | null: false                 |
| cause              | text    | null: false                 |
| suggestion         | text    |                             |
| countermeasure     | text    |                             |

### Association
- (none, since this is a standalone table)


