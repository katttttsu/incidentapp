# IncidentApp データベース設計

## incidents テーブル
| Colum               | Type   | Options                     |
| ------------------- | ------ | --------------------------- |
| level               | int    | null: false                 |
| date                | date   | null: false                 |
| time                | time   | null: false                 |
| place               | string | null: false                 |
| patient_id          | string | null: false                 |
| patient_name        | string | null: false                 |
| patient_age         | int    | null: false                 |
| department          | string | null: false                 |
| job                 | string | null: false                 |
| continuous_service  | int    | null: false                 |
| category1           | string | null: false                 |
| category2           | string | null: false                 |
| situation           | text   | null: false                 |
| cause               | text   | null: false                 |
| suggestion          | text   |                             |
| countermeasure      | text   |                             |

### Association
- (none, since this is a standalone table)

## 管理者パスワード
管理者パスワードは環境変数として設定され、アプリケーションで使用されます。データベースには保存されません。
