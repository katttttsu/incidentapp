IncidentApp
このプロジェクトは、AWS Elastic Beanstalk 環境にデプロイされた Java アプリケーション「IncidentApp」です。このアプリケーションは、JavaFX を使用して作成され、MySQL データベースと接続しています。

プロジェクト概要
IncidentAppは、インシデント管理システムで、ユーザーがインシデントを登録、更新、削除できる機能を提供します。また、インシデントレポートの一覧表示や集計機能も備えています。

主な機能
インシデントの登録、更新、削除
インシデントレポートの一覧表示
インシデントデータの集計とグラフ表示
管理者用ログイン機能
環境
言語: Java 17
フレームワーク: JavaFX
データベース: MySQL
ビルドツール: Gradle 8.3
クラウドプロバイダー: AWS Elastic Beanstalk

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

## 管理者パスワード
管理者パスワードは環境変数として設定され、アプリケーションで使用されます。データベースには保存されません。
