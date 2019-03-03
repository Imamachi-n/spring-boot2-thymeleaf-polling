package com.imamachi.simplepolling;

import com.imamachi.simplepolling.model.*;
import com.imamachi.simplepolling.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    // 登録データ
    private final static String QUESTIONNAIRE_TITLE = "2018年度　有給休暇取得と時間外勤務に関するアンケート";

    private final static String QUESTION_DETAIL_6 = "よかった";
    private final static String QUESTION_DESC2 = "平均的にみて、当社のカスタマーサービス担当者との会話において、不満に感じられる程度を教えてください。";
    private final static String QUESTION_DESC3 = "";

    private boolean alreadySetup = false;
    private QuestionnaireRepository questionnaireRepository;
    private QuestionRepository questionRepository;
    private CurrentQuestionnaireRepository currentQuestionnaireRepository;
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitialDataLoader(QuestionnaireRepository questionnaireRepository,
                             QuestionRepository questionRepository,
                             CurrentQuestionnaireRepository currentQuestionnaireRepository,
                             EmployeeRepository employeeRepository,
                             DepartmentRepository departmentRepository,
                             UserRepository userRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.currentQuestionnaireRepository = currentQuestionnaireRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        // アンケートタイトルをDBに格納
        Questionnaire questionnaire = new Questionnaire(QUESTIONNAIRE_TITLE);
        questionnaireRepository.save(questionnaire);

        // 現在のアンケートを保存
        List<Questionnaire> questionnaire1 = questionnaireRepository.findAll();
        CurrentQuestionnaire currentQuestionnaire = new CurrentQuestionnaire(questionnaire1.get(0));
        currentQuestionnaireRepository.save(currentQuestionnaire);

        // アンケート内容をDBに格納
        String QUESTION_DESC1 = "平均残業時間(1ヶ月)は何時間程度ですか？";
        String QUESTION_DETAIL_1 = "０〜１０時間";
        String QUESTION_DETAIL_2 = "１０〜２０時間";
        String QUESTION_DETAIL_3 = "２０〜３０時間";
        String QUESTION_DETAIL_4 = "３０〜４０時間";
        String QUESTION_DETAIL_5 = "４０時間以上";
        QuestionDetail questionDetail1 = new QuestionDetail(QUESTION_DETAIL_1);
        QuestionDetail questionDetail2 = new QuestionDetail(QUESTION_DETAIL_2);
        QuestionDetail questionDetail3 = new QuestionDetail(QUESTION_DETAIL_3);
        QuestionDetail questionDetail4 = new QuestionDetail(QUESTION_DETAIL_4);
        QuestionDetail questionDetail5 = new QuestionDetail(QUESTION_DETAIL_5);
        Question question = new Question(Question.DocType.singleQ, true, QUESTION_DESC1,
                Arrays.asList(questionDetail1, questionDetail2, questionDetail3, questionDetail4, questionDetail5), questionnaire);
        questionDetail1.setQuestion((question));
        questionDetail2.setQuestion((question));
        questionDetail3.setQuestion((question));
        questionDetail4.setQuestion((question));
        questionDetail5.setQuestion((question));
        questionRepository.save(question);

        String QUESTION_DESC2 = "残業が発生する主な理由は何ですか？（複数回答可）";
        String QUESTION_DETAIL_1_2 = "常に仕事量が多いから";
        String QUESTION_DETAIL_2_2 = "人手不足だから";
        String QUESTION_DETAIL_3_2 = "時期的な業務があるから";
        String QUESTION_DETAIL_4_2 = "年々、業務が複雑化しているから";
        String QUESTION_DETAIL_5_2 = "管理職のマネジメント不足";
        QuestionDetail questionDetail1_2 = new QuestionDetail(QUESTION_DETAIL_1_2);
        QuestionDetail questionDetail2_2 = new QuestionDetail(QUESTION_DETAIL_2_2);
        QuestionDetail questionDetail3_2 = new QuestionDetail(QUESTION_DETAIL_3_2);
        QuestionDetail questionDetail4_2 = new QuestionDetail(QUESTION_DETAIL_4_2);
        QuestionDetail questionDetail5_2 = new QuestionDetail(QUESTION_DETAIL_5_2);
        Question question_2 = new Question(Question.DocType.multiQ, true, QUESTION_DESC2,
                Arrays.asList(questionDetail1_2, questionDetail2_2, questionDetail3_2, questionDetail4_2, questionDetail5_2), questionnaire);
        questionDetail1_2.setQuestion((question_2));
        questionDetail2_2.setQuestion((question_2));
        questionDetail3_2.setQuestion((question_2));
        questionDetail4_2.setQuestion((question_2));
        questionDetail5_2.setQuestion((question_2));
        questionRepository.save(question_2);

        String QUESTION_DESC3 = "実施している取り組みについてお答えください。（複数回答可）";
        String QUESTION_DETAIL_1_3 = "業務分担やフローの見直し";
        String QUESTION_DETAIL_2_3 = "ノー残業デーを設ける";
        String QUESTION_DETAIL_3_3 = "フレックスタイム制など、勤務時間の変更";
        String QUESTION_DETAIL_4_3 = "業務を外注する";
        String QUESTION_DETAIL_5_3 = "残業時間の上限目標を設ける";
        QuestionDetail questionDetail1_3 = new QuestionDetail(QUESTION_DETAIL_1_3);
        QuestionDetail questionDetail2_3 = new QuestionDetail(QUESTION_DETAIL_2_3);
        QuestionDetail questionDetail3_3 = new QuestionDetail(QUESTION_DETAIL_3_3);
        QuestionDetail questionDetail4_3 = new QuestionDetail(QUESTION_DETAIL_4_3);
        QuestionDetail questionDetail5_3 = new QuestionDetail(QUESTION_DETAIL_5_3);
        Question question_3 = new Question(Question.DocType.multiQ, true, QUESTION_DESC3,
                Arrays.asList(questionDetail1_3, questionDetail2_3, questionDetail3_3, questionDetail4_3, questionDetail5_3), questionnaire);
        questionDetail1_3.setQuestion((question_3));
        questionDetail2_3.setQuestion((question_3));
        questionDetail3_3.setQuestion((question_3));
        questionDetail4_3.setQuestion((question_3));
        questionDetail5_3.setQuestion((question_3));
        questionRepository.save(question_3);

        String QUESTION_DESC4 = "今後、残業時間の削減についてどのような対応をお考えですか？";
        String QUESTION_DETAIL_1_4 = "削減に向けて積極的に取り組む";
        String QUESTION_DETAIL_2_4 = "状況に応じて取り組みを検討する";
        String QUESTION_DETAIL_3_4 = "積極的には取り組まない";
        String QUESTION_DETAIL_4_4 = "わからない";
        String QUESTION_DETAIL_5_4 = "その他";
        QuestionDetail questionDetail1_4 = new QuestionDetail(QUESTION_DETAIL_1_4);
        QuestionDetail questionDetail2_4 = new QuestionDetail(QUESTION_DETAIL_2_4);
        QuestionDetail questionDetail3_4 = new QuestionDetail(QUESTION_DETAIL_3_4);
        QuestionDetail questionDetail4_4 = new QuestionDetail(QUESTION_DETAIL_4_4);
        QuestionDetail questionDetail5_4 = new QuestionDetail(QUESTION_DETAIL_5_4);
        Question question_4 = new Question(Question.DocType.singleQ, true, QUESTION_DESC4,
                Arrays.asList(questionDetail1_4, questionDetail2_4, questionDetail3_4, questionDetail4_4, questionDetail5_4), questionnaire);
        questionDetail1_4.setQuestion((question_4));
        questionDetail2_4.setQuestion((question_4));
        questionDetail3_4.setQuestion((question_4));
        questionDetail4_4.setQuestion((question_4));
        questionDetail5_4.setQuestion((question_4));
        questionRepository.save(question_4);

        // アンケート情報をDBに格納
        String QUESTION_DETAIL_X = "自由なコメントをご記入ください。";
        QuestionDetail questionDetail_X = new QuestionDetail(QUESTION_DETAIL_X);
        Question questionX = new Question(Question.DocType.commentQ, true, QUESTION_DETAIL_X,
                Arrays.asList(questionDetail_X), questionnaire);
        questionDetail_X.setQuestion((questionX));
        questionRepository.save(questionX);

        // 従業員ステータスの用意
        Employee employee1 = new Employee("一般社員");
        employeeRepository.save(employee1);
        Employee employee2 = new Employee("管理職");
        employeeRepository.save(employee2);
        Employee employee3 = new Employee("パートナー社員");
        employeeRepository.save(employee3);

        // 部署情報の用意
        Department department1 = new Department("システム開発部");
        departmentRepository.save(department1);
        Department department2 = new Department("ラボシステム部");
        departmentRepository.save(department2);
        Department department3 = new Department("検査システム部");
        departmentRepository.save(department3);
        Department department4 = new Department("顧客システム部");
        departmentRepository.save(department4);
        Department department5 = new Department("システム企画部");
        departmentRepository.save(department5);
        Department department6 = new Department("システム管理部");
        departmentRepository.save(department6);
        Department department7 = new Department("基盤システム部");
        departmentRepository.save(department7);
        Department department8 = new Department("総務部");
        departmentRepository.save(department8);

        // 管理者
        createRoleIfNotFound(Role.RoleName.ADMIN);
        createRoleIfNotFound(Role.RoleName.MANAGER);
        createRoleIfNotFound(Role.RoleName.USER);
        Role adminRole = roleRepository.findByRole(Role.RoleName.ADMIN);
        Role userRole = roleRepository.findByRole(Role.RoleName.USER);

        User user1 = new User("imamachi", "password");
        User user2 = new User("admin", "password");
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        user2.setPassword(passwordEncoder.encode(user2.getPassword()));
        user1.setRoles(Arrays.asList(userRole));
        user2.setRoles(Arrays.asList(adminRole, userRole));
        userRole.setUsers(Arrays.asList(user1));
        adminRole.setUsers(Arrays.asList(user2));
        userRepository.save(user1);
        userRepository.save(user2);
    }

    private Role createRoleIfNotFound(Role.RoleName roleName){
        Role role = roleRepository.findByRole(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }
        return role;
    }
}
