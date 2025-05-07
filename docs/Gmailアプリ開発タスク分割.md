# Gmailメール管理アプリ開発タスク分割（超初心者向け・AI活用版）v2

## はじめに：AIエージェントとの上手な付き合い方

このドキュメントは、あなたがGmailメール管理アプリを開発する上で、「何を」「どの順番で」「どんな技術を使って」作っていくのかをまとめたものです。あなたは主にAIエージェント（例：ChatGPT、Gemini、GitHub Copilotなど）に指示を出して開発を進めるので、各タスクでAIにどんなお願いをすれば良いかのヒントも書きました。

**AIエージェントへの指示のコツ：**
* **具体的に伝える：「何をしてほしいか」を詳しく説明しましょう。**
    * 悪い例：「メール一覧画面を作って」
    * 良い例：「Jetpack Composeを使って、メールの差出人、件名、受信時間を表示するメール一覧画面を作って。データはまだないので、仮のデータ（ダミーデータ）で3件表示してね。見た目は黒背景に白文字でお願い。」
* **一度にたくさん頼みすぎない：** 小さな部品を一つずつ作ってもらうイメージで進めましょう。
* **専門用語も使ってみる：** この資料に出てくる技術の名前（Jetpack Compose、AWS Lambdaなど）をAIに伝えると、より的確なコードや説明が出てきやすくなります。
* **エラーが出たらAIに相談：** うまくいかない時も、エラーメッセージをそのままAIに伝えてみましょう。解決策を教えてくれることがあります。

それでは、タスクを見ていきましょう！

## 1. プロトタイプ (あなたがAIに指示して作るもの) - より詳細な骨組み

まずはアプリの骨組みとなる、見た目の部分（画面）と基本的な動き、そしてデータが流れる仕組みの土台を作ります。ここをしっかり作ることで、チームメンバーが機能追加をしやすくなります。

### 1.1. Androidアプリ フロントエンド (ユーザーが直接触る画面部分)

* **主に使うもの (技術スタック):**
    * **開発言語:** Kotlin (コトリン)
    * **UI作成ツール:** Jetpack Compose (ジェットパック コンポーズ)
    * **開発環境:** Android Studio (アンドロイド スタジオ)
    * **デザインテーマ:** Material Design 3 (マテリアルデザイン スリー) - 黒ベース・白文字のダークテーマ
    * **アーキテクチャ:** MVVM (Model-View-ViewModel) - 画面(View)、画面のデータとロジック(ViewModel)、データ処理(Model)を分ける設計パターン。
    * **非同期処理:** Kotlin Coroutines (コルーチン) - 時間のかかる処理をスムーズに行う技術。
    * **DI (依存性注入):** Hilt (ヒルト) - 必要な部品をうまく管理・注入する道具。
    * **画面遷移:** Jetpack Navigation Component - 画面間の移動を管理する仕組み。

#### 1.1.1. プロジェクト初期設定と基本的なフォルダ構成

* **やること:**
    * Android Studioで新規プロジェクトを作成する（Empty Compose Activity）。
    * MVVMアーキテクチャに基づいた基本的なフォルダ構成（例：`ui/screens`, `ui/viewmodels`, `data/repository`, `di`など）を作成する。
    * Hiltの基本的な設定を`build.gradle`ファイルやApplicationクラスに行う。
* **AIエージェントへの依頼例:**
    * 「Android Jetpack ComposeプロジェクトをMVVMアーキテクチャで始めたい。`ui/screens`、`ui/viewmodels`、`data/repository`、`di`というフォルダ構成の例を教えて。」
    * 「AndroidアプリでHiltをセットアップする手順を教えて。`build.gradle (:app)`と`build.gradle (project)`に必要な記述、そして`@HiltAndroidApp`を付与したApplicationクラスの作成方法を。」

#### 1.1.2. ユーザー認証画面 (ログイン画面) - UIとViewModelの雛形

* **やること (FR-AUTH-001):**
    * ログイン画面のUIを作成（「Googleでサインイン」ボタンのみ）。
    * `LoginViewModel`というViewModelの雛形を作成し、HiltでDIできるようにする。
    * （まだ実際の認証処理はしないが）ボタンタップイベントをViewModelに通知する仕組みだけ作る。
* **AIエージェントへの依頼例:**
    * 「Jetpack Composeでログイン画面を作りたい。画面中央に『Googleでサインイン』ボタンを配置して。この画面に対応する`LoginViewModel`を作成し、HiltでViewModelを画面に注入する基本的なコードを教えて。ボタンがタップされたらViewModelのメソッドを呼ぶようにしたい。」

#### 1.1.3. メール一覧画面 - UIとViewModel、ダミーデータ表示

* **やること (FR-DISP-001, FR-DISP-002の一部):**
    * メール一覧画面のUIを作成（差出人、件名、受信日時、重要度インジケータのプレースホルダー）。
    * `MailListViewModel`を作成し、HiltでDIできるようにする。
    * ViewModel内で仮のメールデータ（`List<MailItem>`のようなデータクラス）をいくつか持ち、それをUIに公開する（StateFlowなどを使用）。
    * UI側ではViewModelからメールリストを受け取り、`LazyColumn`で表示する。
* **AIエージェントへの依頼例:**
    * 「Jetpack Composeでメール一覧画面を作りたい。`MailListViewModel`から`StateFlow<List<MailItem>>`のような形でダミーのメールリスト（差出人、件名、受信日時を含む）を受け取り、`LazyColumn`で表示するコードを教えて。各メールアイテムの左には丸いインジケータ用のスペースも確保して。ViewModelはHiltで注入する前提で。」
    * 「Kotlinで、メールの情報を保持するデータクラス`MailItem`（プロパティ: `id: String`, `sender: String`, `subject: String`, `date: String`, `importance: String`）を作って。」

#### 1.1.4. メール詳細画面 - UIとViewModel、ダミーデータ表示

* **やること (FR-DISP-003の一部):**
    * メール詳細画面のUIを作成（差出人、件名、本文）。
    * `MailDetailViewModel`を作成し、HiltでDIできるようにする。
    * ViewModel内で仮のメール詳細データ（`MailDetail`のようなデータクラス）を持ち、それをUIに公開する。
    * UI側ではViewModelからメール詳細を受け取り表示する。
* **AIエージェントへの依頼例:**
    * 「Jetpack Composeでメール詳細画面を作りたい。`MailDetailViewModel`からメールの詳細情報（差出人、件名、本文）を受け取り表示するコードを教えて。ViewModelはHiltで注入する前提で。」

#### 1.1.5. 基本的な画面遷移 (Jetpack Navigation Component)

* **やること:**
    * Jetpack Navigation Componentを使って、ログイン画面、メール一覧画面、メール詳細画面間の遷移を設定する。
    * メール一覧から詳細画面へ遷移する際に、選択されたメールのIDを渡せるようにする（詳細画面側でIDを受け取り、それに応じたデータをViewModelで取得する準備）。
* **AIエージェントへの依頼例:**
    * 「Jetpack Navigation Componentを使って、3つの画面（LoginScreen, MailListScreen, MailDetailScreen）間のナビゲーションを設定したい。LoginScreenからMailListScreenへ、MailListScreenのアイテムタップでMailDetailScreenへ（メールIDを引数として渡す）遷移する基本的なナビゲーショングラフと`NavController`の使い方のサンプルコードをJetpack Composeで教えて。」

#### 1.1.6. データ層の雛形 (Repositoryパターン - モック実装)

* **やること:**
    * `MailRepository`というインターフェースと、そのダミー実装クラス`MockMailRepositoryImpl`を作成する。
    * `MockMailRepositoryImpl`は、固定のダミーメールリストやダミーメール詳細を返すメソッドを持つ（例: `getMails(): List<MailItem>`, `getMailDetail(id: String): MailDetail`）。
    * Hiltを使って、`MailRepository`のインスタンスをViewModelに注入できるように設定する（最初は`MockMailRepositoryImpl`が使われるように）。
* **AIエージェントへの依頼例:**
    * 「Kotlinで`MailRepository`インターフェース（`suspend fun getMails(): List<MailItem>`と`suspend fun getMailDetail(id: String): MailItem`メソッドを持つ）と、そのダミー実装クラス`MockMailRepositoryImpl`を作りたい。`MockMailRepositoryImpl`は固定のダミーデータを返すようにして。Hiltを使ってこの`MockMailRepositoryImpl`を`MailRepository`としてViewModelに注入するためのDI設定も教えて。」
    * 「`MailListViewModel`と`MailDetailViewModel`で、注入された`MailRepository`を使ってダミーデータを取得し、UIに表示するよう修正して。」

#### 1.1.7. 基本的なUIテーマ (アプリ全体のデザイン統一)

* **やること (NFR-USAB-004):**
    * アプリ全体で、黒ベース・白文字のシンプルなダークテーマを適用します。
* **AIエージェントへの依頼例:**
    * 「Jetpack Composeでアプリ全体にダークテーマを適用したい。`MaterialTheme`を使って、基本的な色（`Color.kt`）やタイポグラフィ（`Type.kt`）、シェイプ（`Shape.kt`）を設定し、`Theme.kt`でアプリのテーマを定義する方法を教えて。Material Design 3の作法に沿ったやり方がいいな。」

---

## 2. チームメンバーがAIに指示して作るもの (またはメンバーが直接作るもの)

あなたが作ったしっかりとした骨組み（プロトタイプ）を元に、チームメンバーが各機能を本格的に実装していきます。

### 2.1. Androidアプリ フロントエンド (ユーザーが直接触る画面部分) - 機能拡充

* **主に使うもの (技術スタック):**
    * **プロトタイプで使ったもの全部** (Kotlin, Jetpack Compose, Android Studio, Material Design 3, MVVM, Coroutines, Hilt, Navigation)
    * **Google Sign-In ライブラリ**
    * **Ktor (ケーター) / Retrofit (レトロフィット):** バックエンドAPIとの通信用。
    * **Android Keystore System:** 機密情報保護用。

#### 2.1.1. ユーザー認証機能 (FR-AUTH) - 本格実装
* **やること:**
    * `FR-AUTH-001`: `LoginViewModel`内でGoogle Sign-Inの実際の処理を実装する。
    * `FR-AUTH-002`: Gmail APIへのアクセス権限（`gmail.readonly`）の同意取得処理を実装する。
    * `FR-AUTH-003`: 認証成功後、バックエンドのCognitoと連携し、必要なトークンを取得・保存する（保存は`AuthRepository`などデータ層で行う）。
    * `FR-AUTH-004`: 設定画面（後述）からのサインアウト機能を実装。ViewModel経由でサインアウト処理（トークン削除など）を呼び出す。
    * `FR-AUTH-005`: サインアウト時のローカルセッション情報クリア処理を実装。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「`LoginViewModel` (HiltViewModel) でGoogle Sign-Inを実装したい。Firebaseは使わず、Google API Consoleで取得したクライアントIDを使って認証し、成功したらIDトークンを取得する。`gmail.readonly`スコープの権限もリクエストしたい。この処理をKotlin Coroutinesを使って非同期で行うサンプルコードを教えて。」
    * 「取得したGoogleのIDトークンをバックエンドのCognitoに渡し、Cognitoの認証情報を得る処理を`AuthRepository`に実装したい。Ktor/Retrofitを使ってバックエンドAPIを呼び出す形で。」

#### 2.1.2. Gmail連携機能 (FR-GMAIL) - フロントエンド側 (バックエンドAPI連携)
* **やること:**
    * `FR-GMAIL-001`: `MailRepository`の実際の実装クラス (`RemoteMailRepositoryImpl`など) を作成し、バックエンドAPIを呼び出して本物のメールデータを取得するようにする。HiltのDI設定をこちらに切り替える。
    * `FR-GMAIL-003`: 取得したメールの詳細情報（差出人アドレス、To/Cc、詳細な日時など）も扱えるようにデータクラスやマッピング処理を修正・追加する。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「Ktor (またはRetrofit) を使って、バックエンドのAPI (例: `/mails`, `/mails/{id}`) からメール一覧やメール詳細を取得する`MailRepository`の実装クラスを作りたい。APIのレスポンスはJSON形式。Kotlin Coroutinesで非同期処理を行うサンプルコードを教えて。Hiltでこの実装を注入するように設定も変更したい。」

#### 2.1.3. メール表示機能 (FR-DISP) - 詳細化・機能追加
* **やること:**
    * `FR-DISP-002`: 差出人名・件名の省略表示、受信日時の詳細な表示形式（「昨日 10:20」など）、重要度インジケータの色分けを実装。
    * `FR-DISP-003`: メール詳細画面で、より多くの情報（To, Ccなど）を表示。HTMLメールの簡易表示。
    * `FR-DISP-004`: 未読メールを太字などで分かりやすく区別する。
    * `FR-DISP-005`: メール一覧を下にスクロールすると、続きのメールを読み込む「無限スクロール」を実装。
    * `FR-DISP-006`: データ読み込み中に「クルクル」（ローディングインジケータ）を表示。
    * `FR-DISP-007`: 表示するメールがない場合に「メールがありません」などのメッセージを表示。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「Jetpack Composeの`LazyColumn`で無限スクロールを実装するには？`MailListViewModel`でページネーションを管理し、リストの最後までスクロールしたら次のデータを`MailRepository`から読み込むようにしたい。」
    * 「受信日時(ISO 8601形式の文字列)を『5分前』『昨日 10:20』『5月7日』のように、現在時刻からの相対時間で表示するKotlinの関数を作って。」

#### 2.1.4. 重要度判定機能 (FR-IMPORT) - フロントエンド側
* **やること:**
    * `FR-IMPORT-004`: バックエンドから受け取ったメールの重要度（高・中・低など）に応じて、メール一覧のインジケータの色を変える。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「`MailItem`データクラスに`importanceCategory: String`（例: "high", "medium", "low"）というプロパティがある。Jetpack Composeのメール一覧アイテムで、このカテゴリに応じてアイコンの色を変えたい。『high』なら赤、『medium』なら青、『low』なら黄色にするにはどうすればいい？」

#### 2.1.5. メールフィルタリング・ソート機能 (FR-FILTER)
* **やること:**
    * `FR-FILTER-001`: 「重要なメールだけ表示」のような絞り込み機能を作る。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「`MailListViewModel`に、重要度でメールをフィルタリングするロジックを追加したい。Jetpack Composeの画面上部にトグルボタンを配置し、ONの時は`MailRepository`から取得したメールリストを重要度でフィルタリングして表示し、OFFの時は全てのメールを表示するようにしたい。」

#### 2.1.6. 設定機能 (FR-SET)
* **やること:**
    * `FR-SET-001`: アプリのバージョン情報を表示する画面を作る。
    * `FR-SET-002`: サインアウト機能。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「Jetpack Composeで設定画面を作りたい。項目として『バージョン情報』と『サインアウト』をリスト表示し、『サインアウト』をタップしたら`AuthViewModel`（または`SettingsViewModel`）のサインアウトメソッドを呼び出すようにしたい。」

#### 2.1.7. 非機能要件対応 (NFR) - フロントエンド関連
* **やること:**
    * パフォーマンス改善、セキュリティ対策（トークンの安全な保存）、使いやすさ向上、クラッシュしにくい安定したアプリにする。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「Jetpack Composeのリスト表示がカクつく場合の一般的な原因と対策は？」
    * 「AndroidのEncryptedSharedPreferencesを使って、アクセストークンなどの機密情報を安全に保存・取得する`AuthRepository`の実装例を教えて。」

### 2.2. バックエンド (AWS - アプリの裏側で動くサーバー機能)

ユーザーの目には直接見えないけれど、メールの取得、AIによる重要度判定、データの保存など、アプリの頭脳となる部分です。

* **主に使うもの (技術スタック):**
    * **主要言語:** Kotlin (Lambda関数もKotlinで書きます)
    * **ビルドツール:** Gradle (グラードル)
    * **コンピューティング:** AWS Lambda (ラムダ)
    * **APIの窓口:** Amazon API Gateway (エーピーアイ ゲートウェイ)
    * **データベース:** Amazon DynamoDB (ダイナモデービー)
    * **ユーザー認証基盤:** Amazon Cognito (コグニート)
    * **秘密情報管理:** AWS Secrets Manager (シークレット マネージャー)
    * **定期実行:** Amazon EventBridge (イベントブリッジ)
    * **アクセス管理:** AWS IAM (アイアム)
    * **API連携:** Gmail API, Gemini API
    * **インフラのコード化 (IaC - 推奨):** AWS CDK / AWS SAM / CloudFormation

(バックエンド以降のタスク内容は前回のドキュメントと同様のため省略。必要に応じてAIエージェントへの依頼例をより具体的に調整してください。)

#### 2.2.1. ユーザー認証基盤 (FR-AUTH)
* **やること:**
    * `FR-AUTH-003`: AWS Cognitoを設定し、AndroidアプリからのGoogle Sign-Inを受け付けて認証できるようにする。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「AWS CognitoのユーザープールとIDプールを作成し、GoogleをIDプロバイダーとして設定する手順を教えて。AndroidアプリからGoogleのIDトークンを使ってCognitoの認証情報を取得するバックエンドAPIをLambda(Kotlin)とAPI Gatewayで作成したい。」
    * 「AWS CDK (TypeScriptまたはKotlin) を使って、上記のCognito設定とAPI Gateway、Lambda関数をコードで定義したい。」

#### 2.2.2. Gmail連携機能 (FR-GMAIL) - バックエンド処理
* **やること:**
    * `FR-GMAIL-001`: Lambda関数でGmail APIを使い、ユーザーの受信トレイからメールを取得する処理を作る。
    * `FR-GMAIL-002`: EventBridgeで、このLambda関数を定期的に（例: 15分ごと）実行するように設定する。
    * `FR-GMAIL-003`: 取得するメール情報（差出人、件名、本文など）を定義し、必要な形に加工する。
    * `FR-GMAIL-004`: Gmail APIキーをSecrets Managerに安全に保管し、Lambda関数から利用する。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「KotlinでAWS Lambda関数を作りたい。Gmail API (Google Client Library for Java) を使って、認証済みユーザーの受信トレイから新しいメールを取得し、差出人、件名、本文（プレーンテキスト）を抽出するコードを書いて。Gmail APIの認証情報はAWS Secrets Managerから取得するようにして。」
    * 「AWS EventBridgeで、特定のLambda関数を15分ごとに定期実行するスケジュールを設定する方法を教えて。AWS CDKでの記述例もあれば嬉しい。」

#### 2.2.3. 重要度判定機能 (FR-IMPORT) - バックエンド処理
* **やること:**
    * `FR-IMPORT-001`: Lambda関数でGemini APIを使い、取得したメールの本文などから重要度を判定させる処理を作る。効果的なプロンプト（AIへの指示文）を工夫する。
    * `FR-IMPORT-002`: Gemini APIからの判定結果（例: 「重要」「普通」など）を、メール情報と一緒にDynamoDBに保存する。
    * `FR-IMPORT-004`: Gemini APIキーをSecrets Managerに安全に保管し、Lambda関数から利用する。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「KotlinのLambda関数内で、Gemini API (Google AI SDK) を使ってメールの重要度を判定したい。メールの件名と本文をインプットとして、『このメールは重要ですか？回答は「高」「中」「低」のいずれかで。理由も簡潔に教えてください』のようなプロンプトでリクエストするサンプルコードを書いて。APIキーはSecrets Managerから取得する前提で。」
    * 「AWS DynamoDBにメールのメタデータ（メールID、ユーザーID、件名、受信日時、Geminiによる重要度カテゴリ、判定理由）を保存するKotlinのコードを教えて。ユーザーIDをパーティションキー、メールIDをソートキーにしたい。」

#### 2.2.4. API開発 (API Gateway)
* **やること:**
    * Androidアプリが必要とする機能（メール一覧取得、メール詳細取得など）を呼び出すための窓口 (APIエンドポイント) をAPI Gatewayで作成し、対応するLambda関数につなげる。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「AWS API GatewayでREST APIを作成し、`/mails` というパスへのGETリクエストを特定のLambda関数にルーティングする設定方法を教えて。Cognitoで認証されたユーザーのみがアクセスできるようにしたい。AWS CDKでの記述例も欲しい。」

#### 2.2.5. 非機能要件対応 (NFR) - バックエンド関連
* **やること:**
    * 処理速度の確保、セキュリティ対策（AWSのベストプラクティスに沿う）、安定稼働、将来の機能追加やユーザー増に対応できるような設計。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「AWS Lambda関数のパフォーマンスを最適化するための一般的なベストプラクティスは何？」
    * 「DynamoDBのテーブル設計で、将来的なデータ量の増加やアクセスパターンの変化に対応しやすくするためのポイントは？」

### 2.3. 全体・その他

* **やること:**
    * システム全体の構成図を作る。
    * CI/CDパイプライン（コード変更時に自動でテストやデプロイをする仕組み）を作る。
    * IaC（インフラをコードで管理する仕組み）を導入する。
    * 使い方や設定方法をまとめたドキュメント (README) を作る。
    * しっかりテストする。
* **AIエージェントへの依頼例 (メンバー向け):**
    * 「PlantUMLやMermaidを使って、このアプリのフロントエンド、バックエンド、AWSサービス間の連携を示す簡単なアーキテクチャ図を描いて。」
    * 「GitHub Actionsを使って、Kotlin (Gradle) のバックエンドプロジェクトをAWS Lambdaに自動デプロイする基本的なワークフローのyamlファイルを書いて。」

## おわりに

このタスク分割とAIエージェントへの依頼例が、あなたのアプリ開発の一助となれば幸いです。最初は難しく感じるかもしれませんが、一つ一つのタスクは小さいので、焦らずじっくり取り組んでみてください。AIエージェントは強力な助っ人ですが、最終的に「何を作りたいか」を考えるのはあなた自身です。楽しみながら開発を進めてくださいね！
